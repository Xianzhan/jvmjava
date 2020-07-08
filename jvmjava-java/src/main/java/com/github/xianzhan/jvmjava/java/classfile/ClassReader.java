package com.github.xianzhan.jvmjava.java.classfile;

import com.github.xianzhan.jvmjava.java.classfile.attribute.BootstrapMethods;
import com.github.xianzhan.jvmjava.java.classfile.attribute.Code;
import com.github.xianzhan.jvmjava.java.classfile.attribute.ConstantValue;
import com.github.xianzhan.jvmjava.java.classfile.attribute.Deprecated;
import com.github.xianzhan.jvmjava.java.classfile.attribute.Exceptions;
import com.github.xianzhan.jvmjava.java.classfile.attribute.LineNumberTable;
import com.github.xianzhan.jvmjava.java.classfile.attribute.LocalVariableTable;
import com.github.xianzhan.jvmjava.java.classfile.attribute.SourceFile;
import com.github.xianzhan.jvmjava.java.classfile.attribute.Synthetic;
import com.github.xianzhan.jvmjava.java.classfile.cp.ClassCp;
import com.github.xianzhan.jvmjava.java.classfile.cp.DoubleCp;
import com.github.xianzhan.jvmjava.java.classfile.cp.FieldDef;
import com.github.xianzhan.jvmjava.java.classfile.cp.FloatCp;
import com.github.xianzhan.jvmjava.java.classfile.cp.IntegerCp;
import com.github.xianzhan.jvmjava.java.classfile.cp.InterfaceMethodDef;
import com.github.xianzhan.jvmjava.java.classfile.cp.InvokeDynamic;
import com.github.xianzhan.jvmjava.java.classfile.cp.LongCp;
import com.github.xianzhan.jvmjava.java.classfile.cp.MethodDef;
import com.github.xianzhan.jvmjava.java.classfile.cp.MethodHandle;
import com.github.xianzhan.jvmjava.java.classfile.cp.MethodType;
import com.github.xianzhan.jvmjava.java.classfile.cp.NameAndType;
import com.github.xianzhan.jvmjava.java.classfile.cp.StringCp;
import com.github.xianzhan.jvmjava.java.classfile.cp.Utf8;
import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.util.CollectionUtils;
import com.github.xianzhan.jvmjava.java.util.IOUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author xianzhan
 * @since 2020-04-11
 */
public class ClassReader {

    public static ClassFile read(DataInputStream dis) throws IOException {
        int magic = readAndCheckMagic(dis);

        int minorVersion = dis.readUnsignedShort();
        int majorVersion = dis.readUnsignedShort();

        int cpSize = dis.readUnsignedShort();
        ConstantPool constantPool = readConstantPool(dis, cpSize - 1);

        int accessFlag = dis.readUnsignedShort();
        int thisClass = dis.readUnsignedShort();
        int superClass = dis.readUnsignedShort();

        int interfaceCount = dis.readUnsignedShort();
        Interfaces interfaces = readInterfaces(dis, interfaceCount, constantPool);

        int fieldCount = dis.readUnsignedShort();
        Fields fields = readFields(dis, fieldCount, constantPool);

        int methodCount = dis.readUnsignedShort();
        Methods methods = readMethods(dis, methodCount, constantPool);

        int attributeCount = dis.readUnsignedShort();
        Attributes attributes = readAttributes(dis, attributeCount, constantPool);

        return new ClassFile(
                magic,
                minorVersion,
                majorVersion,
                cpSize,
                constantPool,
                accessFlag,
                thisClass,
                superClass,
                interfaceCount,
                interfaces,
                fieldCount,
                fields,
                methodCount,
                methods,
                attributeCount,
                attributes
        );
    }

    private static Methods readMethods(DataInputStream dis, int methodCount, ConstantPool constantPool) throws IOException {
        Methods methods = new Methods(methodCount);
        for (int i = 0; i < methodCount; i++) {
            int accessFlag = dis.readUnsignedShort();
            int nameIndex = dis.readUnsignedShort();
            int descriptorIndex = dis.readUnsignedShort();
            int attributesCount = dis.readUnsignedShort();

            String name = constantPool.getString(nameIndex);
            String descriptor = constantPool.getString(descriptorIndex);

            Attributes attributes = readAttributes(dis, attributesCount, constantPool);

            methods.methods[i] = new Method(accessFlag, name, new Descriptor(descriptor), attributes);
        }
        return methods;
    }

    private static Fields readFields(DataInputStream dis, int fieldCount, ConstantPool constantPool) throws IOException {
        Fields fields = new Fields(fieldCount);
        for (int i = 0; i < fieldCount; i++) {
            int accessFlag = dis.readUnsignedShort();
            int nameIndex = dis.readUnsignedShort();
            int descriptorIndex = dis.readUnsignedShort();
            int attributesCount = dis.readUnsignedShort();

            Attributes attributes = readAttributes(dis, attributesCount, constantPool);

            String name = constantPool.getString(nameIndex);
            String descriptor = constantPool.getString(descriptorIndex);

            Field field = new Field(accessFlag, name, new Descriptor(descriptor), attributes);
            fields.fields[i] = field;
        }
        return fields;
    }

    private static Attributes readAttributes(DataInputStream dis, int attributesCount, ConstantPool constantPool) throws IOException {
        Attributes attributes = new Attributes(attributesCount);

        for (int i = 0; i < attributesCount; i++) {
            int attributeNameIndex = dis.readUnsignedShort();
            String attributeName = constantPool.getString(attributeNameIndex);

            int attributeLength = dis.readInt();
            Attribute attribute = switch (attributeName) {
                case Attribute.SourceFile -> {
                    int sourceFileIndex = dis.readUnsignedShort();
                    String file = constantPool.getString(sourceFileIndex);
                    yield new SourceFile(file);
                }
                case Attribute.Code -> {
                    int maxStack = dis.readUnsignedShort();
                    int maxLocals = dis.readUnsignedShort();

                    int codeLength = dis.readInt();
                    byte[] byteCode = IOUtils.readBytes(dis, codeLength);

                    Instruction[] instructions = readInstructions(byteCode, constantPool);

                    int exceptionTableLength = dis.readUnsignedShort();
                    ExceptionTable exceptionTable = readExceptionTable(dis, exceptionTableLength, constantPool);

                    int codeAttributeCount = dis.readUnsignedShort();
                    Attributes codeAttributes = readAttributes(dis, codeAttributeCount, constantPool);

                    yield new Code(maxStack, maxLocals, instructions, exceptionTable, codeAttributes);
                }
                case Attribute.ConstantValue -> {
                    var constantValueIndex = dis.readUnsignedShort();
                    yield new ConstantValue(constantValueIndex);
                }
                case Attribute.Deprecated -> {
                    IOUtils.readBytes(dis, attributeLength);
                    yield new Deprecated();
                }
                case Attribute.Exceptions -> {
                    IOUtils.readBytes(dis, attributeLength);
                    yield new Exceptions();
                }
                case Attribute.LineNumberTable -> {
                    int length = dis.readUnsignedShort();
                    var lines = new LineNumberTable.Line[length];
                    for (int j = 0; j < length; j++) {
                        int startPc = dis.readUnsignedShort();
                        int lineNumber = dis.readUnsignedShort();
                        lines[j] = new LineNumberTable.Line(startPc, lineNumber);
                    }
                    yield new LineNumberTable(lines);
                }
                case Attribute.LocalVariableTable -> {
                    IOUtils.readBytes(dis, attributeLength);
                    yield new LocalVariableTable();
                }
                case Attribute.Synthetic -> {
                    IOUtils.readBytes(dis, attributeLength);
                    yield new Synthetic();
                }
                case Attribute.BootstrapMethods -> {
                    int bsmLen = dis.readUnsignedShort();
                    BootstrapMethods.BootstrapMethod[] bootstrapMethods = new BootstrapMethods.BootstrapMethod[bsmLen];
                    for (int i1 = 0; i1 < bsmLen; i1++) {
                        int bsmr = dis.readUnsignedShort();
                        int nbma = dis.readUnsignedShort();
                        Integer[] args = new Integer[nbma];
                        for (int i2 = 0; i2 < nbma; i2++) {
                            args[i2] = dis.readUnsignedShort();
                        }

                        bootstrapMethods[i1] = new BootstrapMethods.BootstrapMethod(bsmr, args);
                    }

                    yield new BootstrapMethods(bootstrapMethods);
                }
                default -> {
                    IOUtils.readBytes(dis, attributeLength);
                    yield null;
                }
            };
            attributes.attributes[i] = attribute;
        }

        return attributes;
    }

    private static ExceptionTable readExceptionTable(DataInputStream dis, int exceptionTableLength, ConstantPool constantPool) throws IOException {
        Exception[] exceptions = new Exception[exceptionTableLength];
        for (int i = 0; i < exceptionTableLength; i++) {
            int startPc = dis.readUnsignedShort();
            int endPc = dis.readUnsignedShort();
            int handlerPc = dis.readUnsignedShort();
            int clazzIdx = dis.readUnsignedShort();

            String classname;
            if (clazzIdx == 0) {
                // null -> catch any exception
                classname = null;
            } else {
                classname = constantPool.getClassName(clazzIdx);
            }
            var exception = new Exception(startPc, endPc, handlerPc, clazzIdx, classname);
            exceptions[i] = exception;
        }
        return new ExceptionTable(exceptions);
    }

    private static Instruction[] readInstructions(byte[] byteCode, ConstantPool constantPool) throws IOException {
        List<Instruction> instructions = CollectionUtils.newArrayList();
        try (DataInputStream dis = IOUtils.newPaddingDataInputStream(byteCode)) {
            while (dis.available() > 0) {
                var opCode = dis.readUnsignedByte();

                Instruction inst = null;
                try {
                    inst = InstructionReader.read(opCode, dis, constantPool);
                } catch (UnsupportedOperationException e) {
                    System.err.println("UnsupportedOperationException: " + e.getMessage());
                }
                if (inst == null) {
                    System.err.println(Integer.toHexString(opCode));
                    break;
                }

                instructions.add(inst);
            }
        }
        return instructions.toArray(new Instruction[0]);
    }

    private static Interfaces readInterfaces(DataInputStream dis, int interfaceCount, ConstantPool constantPool) throws IOException {
        Interface[] interfaces = new Interface[interfaceCount];
        for (int i = 0; i < interfaceCount; i++) {
            int idx = dis.readUnsignedShort();
            String name = constantPool.getClassName(idx);
            interfaces[i] = new Interface(name);
        }
        return new Interfaces(interfaces);
    }

    private static ConstantPool readConstantPool(DataInputStream dis, int cpSize) throws IOException {
        ConstantPool constantPool = new ConstantPool(cpSize);
        for (int i = 0; i < cpSize; i++) {
            int tag = dis.readUnsignedByte();
            ConstantInfo info = switch (tag) {
                case ConstantInfo.CONSTANT_Fieldref -> new FieldDef(tag, dis.readUnsignedShort(), dis.readUnsignedShort());
                case ConstantInfo.CONSTANT_Methodref -> new MethodDef(tag, dis.readUnsignedShort(), dis.readUnsignedShort());
                case ConstantInfo.CONSTANT_InterfaceMethodref -> new InterfaceMethodDef(tag, dis.readUnsignedShort(), dis.readUnsignedShort());
                case ConstantInfo.CONSTANT_String -> new StringCp(tag, dis.readUnsignedShort());
                case ConstantInfo.CONSTANT_Class -> new ClassCp(tag, dis.readUnsignedShort());
                case ConstantInfo.CONSTANT_NameAndType -> new NameAndType(tag, dis.readUnsignedShort(), dis.readUnsignedShort());
                case ConstantInfo.CONSTANT_Utf8 -> {
                    int length = dis.readUnsignedShort();
                    yield new Utf8(tag, IOUtils.readBytes(dis, length));
                }
                case ConstantInfo.CONSTANT_MethodHandle -> new MethodHandle(tag, dis.readUnsignedByte(), dis.readUnsignedShort());
                case ConstantInfo.CONSTANT_MethodType -> new MethodType(tag, dis.readUnsignedShort());
                case ConstantInfo.CONSTANT_InvokeDynamic -> new InvokeDynamic(tag, dis.readUnsignedShort(), dis.readUnsignedShort());
                case ConstantInfo.CONSTANT_Integer -> new IntegerCp(tag, dis.readInt());
                case ConstantInfo.CONSTANT_Long -> new LongCp(tag, dis.readLong());
                case ConstantInfo.CONSTANT_Float -> new FloatCp(tag, dis.readFloat());
                case ConstantInfo.CONSTANT_Double -> new DoubleCp(tag, dis.readDouble());
                default -> null;
            };

            if (info == null) {
                throw new ClassFormatError("constant pool tag: " + tag);
            }
            info.cp(constantPool);

            constantPool.infos[i] = info;
            if (tag == ConstantInfo.CONSTANT_Long || tag == ConstantInfo.CONSTANT_Double) {
                i++;
            }
        }
        return constantPool;
    }

    private static int readAndCheckMagic(DataInputStream dis) throws IOException {
        int magic = dis.readInt();
        if (magic != ClassFile.MAGIC_NUM) {
            throw new ClassFormatError("magic");
        }
        return magic;
    }
}
