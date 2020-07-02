package com.github.xianzhan.jvmjava.java.classfile;

import com.github.xianzhan.jvmjava.java.bytecode.ByteCodes;
import com.github.xianzhan.jvmjava.java.instruction.Instruction;
import com.github.xianzhan.jvmjava.java.instruction.comparisons.DCmpGInst;
import com.github.xianzhan.jvmjava.java.instruction.comparisons.DCmpLInst;
import com.github.xianzhan.jvmjava.java.instruction.comparisons.FCmpGInst;
import com.github.xianzhan.jvmjava.java.instruction.comparisons.FCmpLInst;
import com.github.xianzhan.jvmjava.java.instruction.comparisons.IfACmpEqInst;
import com.github.xianzhan.jvmjava.java.instruction.comparisons.IfACmpNeInst;
import com.github.xianzhan.jvmjava.java.instruction.comparisons.IfEqInst;
import com.github.xianzhan.jvmjava.java.instruction.comparisons.IfGeInst;
import com.github.xianzhan.jvmjava.java.instruction.comparisons.IfGtInst;
import com.github.xianzhan.jvmjava.java.instruction.comparisons.IfICmpEqInst;
import com.github.xianzhan.jvmjava.java.instruction.comparisons.IfICmpGeInst;
import com.github.xianzhan.jvmjava.java.instruction.comparisons.IfICmpGtInst;
import com.github.xianzhan.jvmjava.java.instruction.comparisons.IfICmpLeInst;
import com.github.xianzhan.jvmjava.java.instruction.comparisons.IfICmpLtInst;
import com.github.xianzhan.jvmjava.java.instruction.comparisons.IfICmpNeInst;
import com.github.xianzhan.jvmjava.java.instruction.comparisons.IfLeInst;
import com.github.xianzhan.jvmjava.java.instruction.comparisons.IfLtInst;
import com.github.xianzhan.jvmjava.java.instruction.comparisons.IfNeInst;
import com.github.xianzhan.jvmjava.java.instruction.comparisons.LCmpInst;
import com.github.xianzhan.jvmjava.java.instruction.constants.AConstNullInst;
import com.github.xianzhan.jvmjava.java.instruction.constants.BIPushInst;
import com.github.xianzhan.jvmjava.java.instruction.constants.DConst0Inst;
import com.github.xianzhan.jvmjava.java.instruction.constants.DConst1Inst;
import com.github.xianzhan.jvmjava.java.instruction.constants.FConst0Inst;
import com.github.xianzhan.jvmjava.java.instruction.constants.FConst1Inst;
import com.github.xianzhan.jvmjava.java.instruction.constants.FConst2Inst;
import com.github.xianzhan.jvmjava.java.instruction.constants.IConst0Inst;
import com.github.xianzhan.jvmjava.java.instruction.constants.IConst1Inst;
import com.github.xianzhan.jvmjava.java.instruction.constants.IConst2Inst;
import com.github.xianzhan.jvmjava.java.instruction.constants.IConst3Inst;
import com.github.xianzhan.jvmjava.java.instruction.constants.IConst4Inst;
import com.github.xianzhan.jvmjava.java.instruction.constants.IConst5Inst;
import com.github.xianzhan.jvmjava.java.instruction.constants.IConstM1Inst;
import com.github.xianzhan.jvmjava.java.instruction.constants.LConst0Inst;
import com.github.xianzhan.jvmjava.java.instruction.constants.LConst1Inst;
import com.github.xianzhan.jvmjava.java.instruction.constants.Ldc2WInst;
import com.github.xianzhan.jvmjava.java.instruction.constants.LdcInst;
import com.github.xianzhan.jvmjava.java.instruction.constants.LdcWInst;
import com.github.xianzhan.jvmjava.java.instruction.constants.NopInst;
import com.github.xianzhan.jvmjava.java.instruction.constants.SIPushInst;
import com.github.xianzhan.jvmjava.java.instruction.control.AReturnInst;
import com.github.xianzhan.jvmjava.java.instruction.control.DReturnInst;
import com.github.xianzhan.jvmjava.java.instruction.control.FReturnInst;
import com.github.xianzhan.jvmjava.java.instruction.control.GotoInst;
import com.github.xianzhan.jvmjava.java.instruction.control.IReturnInst;
import com.github.xianzhan.jvmjava.java.instruction.control.LReturnInst;
import com.github.xianzhan.jvmjava.java.instruction.control.LookupSwitchInst;
import com.github.xianzhan.jvmjava.java.instruction.control.ReturnInst;
import com.github.xianzhan.jvmjava.java.instruction.control.TableSwitchInst;
import com.github.xianzhan.jvmjava.java.instruction.conversions.D2FInst;
import com.github.xianzhan.jvmjava.java.instruction.conversions.D2IInst;
import com.github.xianzhan.jvmjava.java.instruction.conversions.D2LInst;
import com.github.xianzhan.jvmjava.java.instruction.conversions.F2DInst;
import com.github.xianzhan.jvmjava.java.instruction.conversions.F2IInst;
import com.github.xianzhan.jvmjava.java.instruction.conversions.F2LInst;
import com.github.xianzhan.jvmjava.java.instruction.conversions.I2DInst;
import com.github.xianzhan.jvmjava.java.instruction.conversions.I2FInst;
import com.github.xianzhan.jvmjava.java.instruction.conversions.I2LInst;
import com.github.xianzhan.jvmjava.java.instruction.conversions.L2DInst;
import com.github.xianzhan.jvmjava.java.instruction.conversions.L2FInst;
import com.github.xianzhan.jvmjava.java.instruction.conversions.L2IInst;
import com.github.xianzhan.jvmjava.java.instruction.extended.GotoWInst;
import com.github.xianzhan.jvmjava.java.instruction.extended.IfNonNullInst;
import com.github.xianzhan.jvmjava.java.instruction.extended.IfNullInst;
import com.github.xianzhan.jvmjava.java.instruction.extended.WideInst;
import com.github.xianzhan.jvmjava.java.instruction.loads.ALoad0Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.ALoad1Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.ALoad2Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.ALoad3Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.ALoadInst;
import com.github.xianzhan.jvmjava.java.instruction.loads.DLoad0Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.DLoad1Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.DLoad2Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.DLoad3Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.DLoadInst;
import com.github.xianzhan.jvmjava.java.instruction.loads.FLoad0Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.FLoad1Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.FLoad2Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.FLoad3Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.FLoadInst;
import com.github.xianzhan.jvmjava.java.instruction.loads.ILoad0Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.ILoad1Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.ILoad2Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.ILoad3Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.ILoadInst;
import com.github.xianzhan.jvmjava.java.instruction.loads.LLoad0Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.LLoad1Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.LLoad2Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.LLoad3Inst;
import com.github.xianzhan.jvmjava.java.instruction.loads.LLoadInst;
import com.github.xianzhan.jvmjava.java.instruction.math.DAddInst;
import com.github.xianzhan.jvmjava.java.instruction.math.DDivInst;
import com.github.xianzhan.jvmjava.java.instruction.math.DMulInst;
import com.github.xianzhan.jvmjava.java.instruction.math.DNegInst;
import com.github.xianzhan.jvmjava.java.instruction.math.DRemInst;
import com.github.xianzhan.jvmjava.java.instruction.math.DSubInst;
import com.github.xianzhan.jvmjava.java.instruction.math.FAddInst;
import com.github.xianzhan.jvmjava.java.instruction.math.FDivInst;
import com.github.xianzhan.jvmjava.java.instruction.math.FMulInst;
import com.github.xianzhan.jvmjava.java.instruction.math.FNegInst;
import com.github.xianzhan.jvmjava.java.instruction.math.FRemInst;
import com.github.xianzhan.jvmjava.java.instruction.math.FSubInst;
import com.github.xianzhan.jvmjava.java.instruction.math.IAddInst;
import com.github.xianzhan.jvmjava.java.instruction.math.IAndInst;
import com.github.xianzhan.jvmjava.java.instruction.math.IDivInst;
import com.github.xianzhan.jvmjava.java.instruction.math.IIncInst;
import com.github.xianzhan.jvmjava.java.instruction.math.IMulInst;
import com.github.xianzhan.jvmjava.java.instruction.math.INegInst;
import com.github.xianzhan.jvmjava.java.instruction.math.IOrInst;
import com.github.xianzhan.jvmjava.java.instruction.math.IRemInst;
import com.github.xianzhan.jvmjava.java.instruction.math.IShLInst;
import com.github.xianzhan.jvmjava.java.instruction.math.IShRInst;
import com.github.xianzhan.jvmjava.java.instruction.math.ISubInst;
import com.github.xianzhan.jvmjava.java.instruction.math.IUShRInst;
import com.github.xianzhan.jvmjava.java.instruction.math.IXOrInst;
import com.github.xianzhan.jvmjava.java.instruction.math.LAddInst;
import com.github.xianzhan.jvmjava.java.instruction.math.LAndInst;
import com.github.xianzhan.jvmjava.java.instruction.math.LDivInst;
import com.github.xianzhan.jvmjava.java.instruction.math.LMulInst;
import com.github.xianzhan.jvmjava.java.instruction.math.LNegInst;
import com.github.xianzhan.jvmjava.java.instruction.math.LOrInst;
import com.github.xianzhan.jvmjava.java.instruction.math.LRemInst;
import com.github.xianzhan.jvmjava.java.instruction.math.LShLInst;
import com.github.xianzhan.jvmjava.java.instruction.math.LShRInst;
import com.github.xianzhan.jvmjava.java.instruction.math.LSubInst;
import com.github.xianzhan.jvmjava.java.instruction.math.LUShRInst;
import com.github.xianzhan.jvmjava.java.instruction.math.LXOrInst;
import com.github.xianzhan.jvmjava.java.instruction.references.CheckcastInst;
import com.github.xianzhan.jvmjava.java.instruction.references.GetfieldInst;
import com.github.xianzhan.jvmjava.java.instruction.references.GetstaticInst;
import com.github.xianzhan.jvmjava.java.instruction.references.InstanceofInst;
import com.github.xianzhan.jvmjava.java.instruction.references.InvokeinterfaceInst;
import com.github.xianzhan.jvmjava.java.instruction.references.InvokespecialInst;
import com.github.xianzhan.jvmjava.java.instruction.references.InvokestaticInst;
import com.github.xianzhan.jvmjava.java.instruction.references.InvokevirtualInst;
import com.github.xianzhan.jvmjava.java.instruction.references.NewInst;
import com.github.xianzhan.jvmjava.java.instruction.references.PutfieldInst;
import com.github.xianzhan.jvmjava.java.instruction.references.PutstaticInst;
import com.github.xianzhan.jvmjava.java.instruction.stack.Dup2Inst;
import com.github.xianzhan.jvmjava.java.instruction.stack.Dup2X1Inst;
import com.github.xianzhan.jvmjava.java.instruction.stack.Dup2X2Inst;
import com.github.xianzhan.jvmjava.java.instruction.stack.DupInst;
import com.github.xianzhan.jvmjava.java.instruction.stack.DupX1Inst;
import com.github.xianzhan.jvmjava.java.instruction.stack.DupX2Inst;
import com.github.xianzhan.jvmjava.java.instruction.stack.Pop2Inst;
import com.github.xianzhan.jvmjava.java.instruction.stack.PopInst;
import com.github.xianzhan.jvmjava.java.instruction.stack.SwapInst;
import com.github.xianzhan.jvmjava.java.instruction.stores.AStore0Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.AStore1Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.AStore2Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.AStore3Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.AStoreInst;
import com.github.xianzhan.jvmjava.java.instruction.stores.DStore0Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.DStore1Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.DStore2Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.DStore3Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.DStoreInst;
import com.github.xianzhan.jvmjava.java.instruction.stores.FStore0Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.FStore1Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.FStore2Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.FStore3Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.FStoreInst;
import com.github.xianzhan.jvmjava.java.instruction.stores.IStore0Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.IStore1Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.IStore2Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.IStore3Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.IStoreInst;
import com.github.xianzhan.jvmjava.java.instruction.stores.LStore0Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.LStore1Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.LStore2Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.LStore3Inst;
import com.github.xianzhan.jvmjava.java.instruction.stores.LStoreInst;
import com.github.xianzhan.jvmjava.java.util.IOUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 指令读取
 *
 * @author xianzhan
 * @since 2020-06-17
 */
public class InstructionReader {

    public static Instruction read(int opCode, DataInputStream dis, ConstantPool constantPool) throws IOException {
        return switch (opCode) {
            case ByteCodes.nop -> new NopInst();
            case ByteCodes.aconst_null -> new AConstNullInst();
            case ByteCodes.iconst_m1 -> new IConstM1Inst();
            case ByteCodes.iconst_0 -> new IConst0Inst();
            case ByteCodes.iconst_1 -> new IConst1Inst();
            case ByteCodes.iconst_2 -> new IConst2Inst();
            case ByteCodes.iconst_3 -> new IConst3Inst();
            case ByteCodes.iconst_4 -> new IConst4Inst();
            case ByteCodes.iconst_5 -> new IConst5Inst();
            case ByteCodes.lconst_0 -> new LConst0Inst();
            case ByteCodes.lconst_1 -> new LConst1Inst();
            case ByteCodes.fconst_0 -> new FConst0Inst();
            case ByteCodes.fconst_1 -> new FConst1Inst();
            case ByteCodes.fconst_2 -> new FConst2Inst();
            case ByteCodes.dconst_0 -> new DConst0Inst();
            case ByteCodes.dconst_1 -> new DConst1Inst();

            case ByteCodes.bipush -> new BIPushInst(dis.readByte());
            case ByteCodes.sipush -> new SIPushInst(dis.readShort());

            case ByteCodes.ldc1 -> new LdcInst(dis.readUnsignedByte());
            case ByteCodes.ldc2 -> new LdcWInst(dis.readUnsignedShort());
            case ByteCodes.ldc2w -> new Ldc2WInst(dis.readUnsignedShort());

            case ByteCodes.iload -> new ILoadInst(dis.readUnsignedByte());
            case ByteCodes.lload -> new LLoadInst(dis.readUnsignedByte());
            case ByteCodes.fload -> new FLoadInst(dis.readUnsignedByte());
            case ByteCodes.dload -> new DLoadInst(dis.readUnsignedByte());
            case ByteCodes.aload -> new ALoadInst(dis.readUnsignedByte());
            case ByteCodes.iload_0 -> new ILoad0Inst();
            case ByteCodes.iload_1 -> new ILoad1Inst();
            case ByteCodes.iload_2 -> new ILoad2Inst();
            case ByteCodes.iload_3 -> new ILoad3Inst();
            case ByteCodes.lload_0 -> new LLoad0Inst();
            case ByteCodes.lload_1 -> new LLoad1Inst();
            case ByteCodes.lload_2 -> new LLoad2Inst();
            case ByteCodes.lload_3 -> new LLoad3Inst();
            case ByteCodes.fload_0 -> new FLoad0Inst();
            case ByteCodes.fload_1 -> new FLoad1Inst();
            case ByteCodes.fload_2 -> new FLoad2Inst();
            case ByteCodes.fload_3 -> new FLoad3Inst();
            case ByteCodes.dload_0 -> new DLoad0Inst();
            case ByteCodes.dload_1 -> new DLoad1Inst();
            case ByteCodes.dload_2 -> new DLoad2Inst();
            case ByteCodes.dload_3 -> new DLoad3Inst();
            case ByteCodes.aload_0 -> new ALoad0Inst();
            case ByteCodes.aload_1 -> new ALoad1Inst();
            case ByteCodes.aload_2 -> new ALoad2Inst();
            case ByteCodes.aload_3 -> new ALoad3Inst();
            case ByteCodes.iaload -> throw new UnsupportedOperationException("iaload");
            case ByteCodes.laload -> throw new UnsupportedOperationException("laload");
            case ByteCodes.faload -> throw new UnsupportedOperationException("faload");
            case ByteCodes.daload -> throw new UnsupportedOperationException("daload");
            case ByteCodes.aaload -> throw new UnsupportedOperationException("aaload");
            case ByteCodes.baload -> throw new UnsupportedOperationException("baload");
            case ByteCodes.caload -> throw new UnsupportedOperationException("caload");
            case ByteCodes.saload -> throw new UnsupportedOperationException("saload");

            case ByteCodes.istore -> new IStoreInst(dis.readUnsignedByte());
            case ByteCodes.lstore -> new LStoreInst(dis.readUnsignedByte());
            case ByteCodes.fstore -> new FStoreInst(dis.readUnsignedByte());
            case ByteCodes.dstore -> new DStoreInst(dis.readUnsignedByte());
            case ByteCodes.astore -> new AStoreInst(dis.readUnsignedByte());
            case ByteCodes.istore_0 -> new IStore0Inst();
            case ByteCodes.istore_1 -> new IStore1Inst();
            case ByteCodes.istore_2 -> new IStore2Inst();
            case ByteCodes.istore_3 -> new IStore3Inst();
            case ByteCodes.lstore_0 -> new LStore0Inst();
            case ByteCodes.lstore_1 -> new LStore1Inst();
            case ByteCodes.lstore_2 -> new LStore2Inst();
            case ByteCodes.lstore_3 -> new LStore3Inst();
            case ByteCodes.fstore_0 -> new FStore0Inst();
            case ByteCodes.fstore_1 -> new FStore1Inst();
            case ByteCodes.fstore_2 -> new FStore2Inst();
            case ByteCodes.fstore_3 -> new FStore3Inst();
            case ByteCodes.dstore_0 -> new DStore0Inst();
            case ByteCodes.dstore_1 -> new DStore1Inst();
            case ByteCodes.dstore_2 -> new DStore2Inst();
            case ByteCodes.dstore_3 -> new DStore3Inst();
            case ByteCodes.astore_0 -> new AStore0Inst();
            case ByteCodes.astore_1 -> new AStore1Inst();
            case ByteCodes.astore_2 -> new AStore2Inst();
            case ByteCodes.astore_3 -> new AStore3Inst();
            case ByteCodes.iastore -> throw new UnsupportedOperationException("iastore");
            case ByteCodes.lastore -> throw new UnsupportedOperationException("lastore");
            case ByteCodes.fastore -> throw new UnsupportedOperationException("fastore");
            case ByteCodes.dastore -> throw new UnsupportedOperationException("dastore");
            case ByteCodes.aastore -> throw new UnsupportedOperationException("aastore");
            case ByteCodes.bastore -> throw new UnsupportedOperationException("bastore");
            case ByteCodes.castore -> throw new UnsupportedOperationException("castore");
            case ByteCodes.sastore -> throw new UnsupportedOperationException("sastore");

            case ByteCodes.pop -> new PopInst();
            case ByteCodes.pop2 -> new Pop2Inst();
            case ByteCodes.dup -> new DupInst();
            case ByteCodes.dup_x1 -> new DupX1Inst();
            case ByteCodes.dup_x2 -> new DupX2Inst();
            case ByteCodes.dup2 -> new Dup2Inst();
            case ByteCodes.dup2_x1 -> new Dup2X1Inst();
            case ByteCodes.dup2_x2 -> new Dup2X2Inst();
            case ByteCodes.swap -> new SwapInst();

            case ByteCodes.iadd -> new IAddInst();
            case ByteCodes.ladd -> new LAddInst();
            case ByteCodes.fadd -> new FAddInst();
            case ByteCodes.dadd -> new DAddInst();

            case ByteCodes.isub -> new ISubInst();
            case ByteCodes.lsub -> new LSubInst();
            case ByteCodes.fsub -> new FSubInst();
            case ByteCodes.dsub -> new DSubInst();

            case ByteCodes.imul -> new IMulInst();
            case ByteCodes.lmul -> new LMulInst();
            case ByteCodes.fmul -> new FMulInst();
            case ByteCodes.dmul -> new DMulInst();

            case ByteCodes.idiv -> new IDivInst();
            case ByteCodes.ldiv -> new LDivInst();
            case ByteCodes.fdiv -> new FDivInst();
            case ByteCodes.ddiv -> new DDivInst();

            case ByteCodes.imod -> new IRemInst();
            case ByteCodes.lmod -> new LRemInst();
            case ByteCodes.fmod -> new FRemInst();
            case ByteCodes.dmod -> new DRemInst();

            case ByteCodes.ineg -> new INegInst();
            case ByteCodes.lneg -> new LNegInst();
            case ByteCodes.fneg -> new FNegInst();
            case ByteCodes.dneg -> new DNegInst();

            case ByteCodes.ishl -> new IShLInst();
            case ByteCodes.lshl -> new LShLInst();
            case ByteCodes.ishr -> new IShRInst();
            case ByteCodes.lshr -> new LShRInst();
            case ByteCodes.iushr -> new IUShRInst();
            case ByteCodes.lushr -> new LUShRInst();

            case ByteCodes.iand -> new IAndInst();
            case ByteCodes.land -> new LAndInst();
            case ByteCodes.ior -> new IOrInst();
            case ByteCodes.lor -> new LOrInst();
            case ByteCodes.ixor -> new IXOrInst();
            case ByteCodes.lxor -> new LXOrInst();

            case ByteCodes.iinc -> new IIncInst(dis.readUnsignedByte(), dis.readByte());

            case ByteCodes.i2l -> new I2LInst();
            case ByteCodes.i2f -> new I2FInst();
            case ByteCodes.i2d -> new I2DInst();
            case ByteCodes.l2i -> new L2IInst();
            case ByteCodes.l2f -> new L2FInst();
            case ByteCodes.l2d -> new L2DInst();
            case ByteCodes.f2i -> new F2IInst();
            case ByteCodes.f2l -> new F2LInst();
            case ByteCodes.f2d -> new F2DInst();
            case ByteCodes.d2i -> new D2IInst();
            case ByteCodes.d2l -> new D2LInst();
            case ByteCodes.d2f -> new D2FInst();
            case ByteCodes.int2byte -> throw new UnsupportedOperationException("int2byte");
            case ByteCodes.int2char -> throw new UnsupportedOperationException("int2char");
            case ByteCodes.int2short -> throw new UnsupportedOperationException("int2short");

            case ByteCodes.lcmp -> new LCmpInst();
            case ByteCodes.fcmpl -> new FCmpLInst();
            case ByteCodes.fcmpg -> new FCmpGInst();
            case ByteCodes.dcmpl -> new DCmpLInst();
            case ByteCodes.dcmpg -> new DCmpGInst();

            case ByteCodes.ifeq -> new IfEqInst(dis.readShort());
            case ByteCodes.ifne -> new IfNeInst(dis.readShort());
            case ByteCodes.iflt -> new IfLtInst(dis.readShort());
            case ByteCodes.ifge -> new IfGeInst(dis.readShort());
            case ByteCodes.ifgt -> new IfGtInst(dis.readShort());
            case ByteCodes.ifle -> new IfLeInst(dis.readShort());
            case ByteCodes.if_icmpeq -> new IfICmpEqInst(dis.readShort());
            case ByteCodes.if_icmpne -> new IfICmpNeInst(dis.readShort());
            case ByteCodes.if_icmplt -> new IfICmpLtInst(dis.readShort());
            case ByteCodes.if_icmpge -> new IfICmpGeInst(dis.readShort());
            case ByteCodes.if_icmpgt -> new IfICmpGtInst(dis.readShort());
            case ByteCodes.if_icmple -> new IfICmpLeInst(dis.readShort());
            case ByteCodes.if_acmpeq -> new IfACmpEqInst(dis.readShort());
            case ByteCodes.if_acmpne -> new IfACmpNeInst(dis.readShort());

            case ByteCodes.goto_ -> new GotoInst(dis.readShort());

            // jsr, 1.6 以前使用, 忽略
            case ByteCodes.jsr -> throw new UnsupportedOperationException("jsr");
            // ret, 1.6 以前使用, 忽略
            case ByteCodes.ret -> throw new UnsupportedOperationException("ret");

            case ByteCodes.tableswitch -> {
                int offset = 1;

                int padding = IOUtils.readPadding(dis);
                offset += padding;

                int tsDefault = dis.readInt();
                int tsLow = dis.readInt();
                int tsHigh = dis.readInt();
                offset += 12;

                int tsOffsetByteLength = (tsHigh - tsLow + 1) * 4;
                Map<Integer, Integer> map = new LinkedHashMap<>();
                for (int i = tsLow; i <= tsHigh; i++) {
                    map.put(i, dis.readInt());
                }
                offset += tsOffsetByteLength;

                yield new TableSwitchInst(offset, tsDefault, tsLow, tsHigh, map);
            }
            case ByteCodes.lookupswitch -> {
                int lsOffset = 1;
                int lsPadding = IOUtils.readPadding(dis);
                lsOffset += lsPadding;

                int lsDef = dis.readInt();
                lsOffset += 4;
                int lsPairsCnt = dis.readInt();
                lsOffset += 4;

                int lsPairsLen = lsPairsCnt * 2 * 4;
                Map<Integer, Integer> lsMap = new LinkedHashMap<>();
                for (int i = 0; i < lsPairsCnt; i++) {
                    lsMap.put(dis.readInt(), dis.readInt());
                }

                lsOffset += lsPairsLen;
                yield new LookupSwitchInst(lsOffset, lsDef, lsPairsCnt, lsMap);
            }

            case ByteCodes.ireturn -> new IReturnInst();
            case ByteCodes.lreturn -> new LReturnInst();
            case ByteCodes.freturn -> new FReturnInst();
            case ByteCodes.dreturn -> new DReturnInst();
            case ByteCodes.areturn -> new AReturnInst();
            case ByteCodes.return_ -> new ReturnInst();

            case ByteCodes.getstatic -> new GetstaticInst(dis.readUnsignedShort());
            case ByteCodes.putstatic -> new PutstaticInst(dis.readUnsignedShort());

            case ByteCodes.getfield -> new GetfieldInst(dis.readUnsignedShort());
            case ByteCodes.putfield -> new PutfieldInst(dis.readUnsignedShort());

            case ByteCodes.invokevirtual -> new InvokevirtualInst(dis.readUnsignedShort());
            case ByteCodes.invokespecial -> {
                var index = dis.readUnsignedShort();
                yield new InvokespecialInst(index);
            }
            case ByteCodes.invokestatic -> new InvokestaticInst(dis.readUnsignedShort());
            case ByteCodes.invokeinterface -> new InvokeinterfaceInst(
                    dis.readUnsignedShort(), dis.readUnsignedByte(), dis.readUnsignedByte()
            );
            case ByteCodes.invokedynamic -> throw new UnsupportedOperationException("invokedynamic");

            case ByteCodes.new_ -> new NewInst(dis.readUnsignedShort());
            case ByteCodes.newarray -> throw new UnsupportedOperationException("newarray");
            case ByteCodes.anewarray -> throw new UnsupportedOperationException("anewarray");
            case ByteCodes.arraylength -> throw new UnsupportedOperationException("arraylength");

            case ByteCodes.athrow -> throw new UnsupportedOperationException("athrow");

            case ByteCodes.checkcast -> {
                var i = dis.readUnsignedShort();
                var className = constantPool.getClassName(i);
                yield new CheckcastInst(i, className);
            }
            case ByteCodes.instanceof_ -> new InstanceofInst(dis.readUnsignedShort());

            case ByteCodes.monitorenter -> throw new UnsupportedOperationException("monitorenter");
            case ByteCodes.monitorexit -> throw new UnsupportedOperationException("monitorexit");

            case ByteCodes.wide -> {
                int wideOpcode = dis.readUnsignedByte();
                yield switch (wideOpcode) {
                    case ByteCodes.iinc -> new WideInst(6, new IIncInst(dis.readUnsignedShort(), dis.readShort()));

                    case ByteCodes.iload -> new WideInst(4, new ILoadInst(dis.readUnsignedShort()));
                    case ByteCodes.fload -> new WideInst(4, new FLoadInst(dis.readUnsignedShort()));
                    case ByteCodes.aload -> new WideInst(4, new ALoadInst(dis.readUnsignedShort()));
                    case ByteCodes.lload -> new WideInst(4, new LLoadInst(dis.readUnsignedShort()));
                    case ByteCodes.dload -> new WideInst(4, new DLoadInst(dis.readUnsignedShort()));

                    case ByteCodes.istore -> new WideInst(4, new IStoreInst(dis.readUnsignedShort()));
                    case ByteCodes.fstore -> new WideInst(4, new FStoreInst(dis.readUnsignedShort()));
                    case ByteCodes.astore -> new WideInst(4, new AStoreInst(dis.readUnsignedShort()));
                    case ByteCodes.lstore -> new WideInst(4, new LStoreInst(dis.readUnsignedShort()));
                    case ByteCodes.dstore -> new WideInst(4, new DStoreInst(dis.readUnsignedShort()));
                    // ret, ignore
                    case ByteCodes.ret -> throw new UnsupportedOperationException();
                    default -> throw new UnsupportedOperationException("wide op " + wideOpcode);
                };
            }

            case ByteCodes.multianewarray -> throw new UnsupportedOperationException("multianewarray");
            case ByteCodes.if_acmp_null -> new IfNullInst(dis.readShort());
            case ByteCodes.if_acmp_nonnull -> new IfNonNullInst(dis.readShort());
            case ByteCodes.goto_w -> new GotoWInst(dis.readInt());
            // jsr_w, 同 jsr, 忽略
            case ByteCodes.jsr_w -> throw new UnsupportedOperationException();

            default -> null;
        };
    }
}
