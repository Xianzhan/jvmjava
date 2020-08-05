package xianzhan.jvmjava.java.classfile;

import xianzhan.jvmjava.java.bytecode.ByteCodes;
import xianzhan.jvmjava.java.instruction.Instruction;
import xianzhan.jvmjava.java.instruction.comparisons.DCmpGInst;
import xianzhan.jvmjava.java.instruction.comparisons.DCmpLInst;
import xianzhan.jvmjava.java.instruction.comparisons.FCmpGInst;
import xianzhan.jvmjava.java.instruction.comparisons.FCmpLInst;
import xianzhan.jvmjava.java.instruction.comparisons.IfACmpEqInst;
import xianzhan.jvmjava.java.instruction.comparisons.IfACmpNeInst;
import xianzhan.jvmjava.java.instruction.comparisons.IfEqInst;
import xianzhan.jvmjava.java.instruction.comparisons.IfGeInst;
import xianzhan.jvmjava.java.instruction.comparisons.IfGtInst;
import xianzhan.jvmjava.java.instruction.comparisons.IfICmpEqInst;
import xianzhan.jvmjava.java.instruction.comparisons.IfICmpGeInst;
import xianzhan.jvmjava.java.instruction.comparisons.IfICmpGtInst;
import xianzhan.jvmjava.java.instruction.comparisons.IfICmpLeInst;
import xianzhan.jvmjava.java.instruction.comparisons.IfICmpLtInst;
import xianzhan.jvmjava.java.instruction.comparisons.IfICmpNeInst;
import xianzhan.jvmjava.java.instruction.comparisons.IfLeInst;
import xianzhan.jvmjava.java.instruction.comparisons.IfLtInst;
import xianzhan.jvmjava.java.instruction.comparisons.IfNeInst;
import xianzhan.jvmjava.java.instruction.comparisons.LCmpInst;
import xianzhan.jvmjava.java.instruction.constants.AConstNullInst;
import xianzhan.jvmjava.java.instruction.constants.BIPushInst;
import xianzhan.jvmjava.java.instruction.constants.DConst0Inst;
import xianzhan.jvmjava.java.instruction.constants.DConst1Inst;
import xianzhan.jvmjava.java.instruction.constants.FConst0Inst;
import xianzhan.jvmjava.java.instruction.constants.FConst1Inst;
import xianzhan.jvmjava.java.instruction.constants.FConst2Inst;
import xianzhan.jvmjava.java.instruction.constants.IConst0Inst;
import xianzhan.jvmjava.java.instruction.constants.IConst1Inst;
import xianzhan.jvmjava.java.instruction.constants.IConst2Inst;
import xianzhan.jvmjava.java.instruction.constants.IConst3Inst;
import xianzhan.jvmjava.java.instruction.constants.IConst4Inst;
import xianzhan.jvmjava.java.instruction.constants.IConst5Inst;
import xianzhan.jvmjava.java.instruction.constants.IConstM1Inst;
import xianzhan.jvmjava.java.instruction.constants.Int2ByteInst;
import xianzhan.jvmjava.java.instruction.constants.Int2CharInst;
import xianzhan.jvmjava.java.instruction.constants.Int2ShortInst;
import xianzhan.jvmjava.java.instruction.constants.LConst0Inst;
import xianzhan.jvmjava.java.instruction.constants.LConst1Inst;
import xianzhan.jvmjava.java.instruction.constants.Ldc2WInst;
import xianzhan.jvmjava.java.instruction.constants.LdcInst;
import xianzhan.jvmjava.java.instruction.constants.LdcWInst;
import xianzhan.jvmjava.java.instruction.constants.NopInst;
import xianzhan.jvmjava.java.instruction.constants.SIPushInst;
import xianzhan.jvmjava.java.instruction.control.AReturnInst;
import xianzhan.jvmjava.java.instruction.control.DReturnInst;
import xianzhan.jvmjava.java.instruction.control.FReturnInst;
import xianzhan.jvmjava.java.instruction.control.GotoInst;
import xianzhan.jvmjava.java.instruction.control.IReturnInst;
import xianzhan.jvmjava.java.instruction.control.LReturnInst;
import xianzhan.jvmjava.java.instruction.control.LookupSwitchInst;
import xianzhan.jvmjava.java.instruction.control.ReturnInst;
import xianzhan.jvmjava.java.instruction.control.TableSwitchInst;
import xianzhan.jvmjava.java.instruction.conversions.D2FInst;
import xianzhan.jvmjava.java.instruction.conversions.D2IInst;
import xianzhan.jvmjava.java.instruction.conversions.D2LInst;
import xianzhan.jvmjava.java.instruction.conversions.F2DInst;
import xianzhan.jvmjava.java.instruction.conversions.F2IInst;
import xianzhan.jvmjava.java.instruction.conversions.F2LInst;
import xianzhan.jvmjava.java.instruction.conversions.I2DInst;
import xianzhan.jvmjava.java.instruction.conversions.I2FInst;
import xianzhan.jvmjava.java.instruction.conversions.I2LInst;
import xianzhan.jvmjava.java.instruction.conversions.L2DInst;
import xianzhan.jvmjava.java.instruction.conversions.L2FInst;
import xianzhan.jvmjava.java.instruction.conversions.L2IInst;
import xianzhan.jvmjava.java.instruction.extended.GotoWInst;
import xianzhan.jvmjava.java.instruction.extended.IfNonNullInst;
import xianzhan.jvmjava.java.instruction.extended.IfNullInst;
import xianzhan.jvmjava.java.instruction.extended.WideInst;
import xianzhan.jvmjava.java.instruction.loads.AALoadInst;
import xianzhan.jvmjava.java.instruction.loads.ALoad0Inst;
import xianzhan.jvmjava.java.instruction.loads.ALoad1Inst;
import xianzhan.jvmjava.java.instruction.loads.ALoad2Inst;
import xianzhan.jvmjava.java.instruction.loads.ALoad3Inst;
import xianzhan.jvmjava.java.instruction.loads.ALoadInst;
import xianzhan.jvmjava.java.instruction.loads.BALoadInst;
import xianzhan.jvmjava.java.instruction.loads.CALoadInst;
import xianzhan.jvmjava.java.instruction.loads.DALoadInst;
import xianzhan.jvmjava.java.instruction.loads.DLoad0Inst;
import xianzhan.jvmjava.java.instruction.loads.DLoad1Inst;
import xianzhan.jvmjava.java.instruction.loads.DLoad2Inst;
import xianzhan.jvmjava.java.instruction.loads.DLoad3Inst;
import xianzhan.jvmjava.java.instruction.loads.DLoadInst;
import xianzhan.jvmjava.java.instruction.loads.FALoadInst;
import xianzhan.jvmjava.java.instruction.loads.FLoad0Inst;
import xianzhan.jvmjava.java.instruction.loads.FLoad1Inst;
import xianzhan.jvmjava.java.instruction.loads.FLoad2Inst;
import xianzhan.jvmjava.java.instruction.loads.FLoad3Inst;
import xianzhan.jvmjava.java.instruction.loads.FLoadInst;
import xianzhan.jvmjava.java.instruction.loads.IALoadInst;
import xianzhan.jvmjava.java.instruction.loads.ILoad0Inst;
import xianzhan.jvmjava.java.instruction.loads.ILoad1Inst;
import xianzhan.jvmjava.java.instruction.loads.ILoad2Inst;
import xianzhan.jvmjava.java.instruction.loads.ILoad3Inst;
import xianzhan.jvmjava.java.instruction.loads.ILoadInst;
import xianzhan.jvmjava.java.instruction.loads.LALoadInst;
import xianzhan.jvmjava.java.instruction.loads.LLoad0Inst;
import xianzhan.jvmjava.java.instruction.loads.LLoad1Inst;
import xianzhan.jvmjava.java.instruction.loads.LLoad2Inst;
import xianzhan.jvmjava.java.instruction.loads.LLoad3Inst;
import xianzhan.jvmjava.java.instruction.loads.LLoadInst;
import xianzhan.jvmjava.java.instruction.loads.SALoadInst;
import xianzhan.jvmjava.java.instruction.math.DAddInst;
import xianzhan.jvmjava.java.instruction.math.DDivInst;
import xianzhan.jvmjava.java.instruction.math.DMulInst;
import xianzhan.jvmjava.java.instruction.math.DNegInst;
import xianzhan.jvmjava.java.instruction.math.DRemInst;
import xianzhan.jvmjava.java.instruction.math.DSubInst;
import xianzhan.jvmjava.java.instruction.math.FAddInst;
import xianzhan.jvmjava.java.instruction.math.FDivInst;
import xianzhan.jvmjava.java.instruction.math.FMulInst;
import xianzhan.jvmjava.java.instruction.math.FNegInst;
import xianzhan.jvmjava.java.instruction.math.FRemInst;
import xianzhan.jvmjava.java.instruction.math.FSubInst;
import xianzhan.jvmjava.java.instruction.math.IAddInst;
import xianzhan.jvmjava.java.instruction.math.IAndInst;
import xianzhan.jvmjava.java.instruction.math.IDivInst;
import xianzhan.jvmjava.java.instruction.math.IIncInst;
import xianzhan.jvmjava.java.instruction.math.IMulInst;
import xianzhan.jvmjava.java.instruction.math.INegInst;
import xianzhan.jvmjava.java.instruction.math.IOrInst;
import xianzhan.jvmjava.java.instruction.math.IRemInst;
import xianzhan.jvmjava.java.instruction.math.IShLInst;
import xianzhan.jvmjava.java.instruction.math.IShRInst;
import xianzhan.jvmjava.java.instruction.math.ISubInst;
import xianzhan.jvmjava.java.instruction.math.IUShRInst;
import xianzhan.jvmjava.java.instruction.math.IXOrInst;
import xianzhan.jvmjava.java.instruction.math.LAddInst;
import xianzhan.jvmjava.java.instruction.math.LAndInst;
import xianzhan.jvmjava.java.instruction.math.LDivInst;
import xianzhan.jvmjava.java.instruction.math.LMulInst;
import xianzhan.jvmjava.java.instruction.math.LNegInst;
import xianzhan.jvmjava.java.instruction.math.LOrInst;
import xianzhan.jvmjava.java.instruction.math.LRemInst;
import xianzhan.jvmjava.java.instruction.math.LShLInst;
import xianzhan.jvmjava.java.instruction.math.LShRInst;
import xianzhan.jvmjava.java.instruction.math.LSubInst;
import xianzhan.jvmjava.java.instruction.math.LUShRInst;
import xianzhan.jvmjava.java.instruction.math.LXOrInst;
import xianzhan.jvmjava.java.instruction.references.ANewArrayInst;
import xianzhan.jvmjava.java.instruction.references.AThrowInst;
import xianzhan.jvmjava.java.instruction.references.ArrayLengthInst;
import xianzhan.jvmjava.java.instruction.references.CheckcastInst;
import xianzhan.jvmjava.java.instruction.references.GetfieldInst;
import xianzhan.jvmjava.java.instruction.references.GetstaticInst;
import xianzhan.jvmjava.java.instruction.references.InstanceofInst;
import xianzhan.jvmjava.java.instruction.references.InvokeinterfaceInst;
import xianzhan.jvmjava.java.instruction.references.InvokespecialInst;
import xianzhan.jvmjava.java.instruction.references.InvokestaticInst;
import xianzhan.jvmjava.java.instruction.references.InvokevirtualInst;
import xianzhan.jvmjava.java.instruction.references.MultiANewArrayInst;
import xianzhan.jvmjava.java.instruction.references.NewArrayInst;
import xianzhan.jvmjava.java.instruction.references.NewInst;
import xianzhan.jvmjava.java.instruction.references.PutfieldInst;
import xianzhan.jvmjava.java.instruction.references.PutstaticInst;
import xianzhan.jvmjava.java.instruction.reserved.InvokenativeInst;
import xianzhan.jvmjava.java.instruction.stack.Dup2Inst;
import xianzhan.jvmjava.java.instruction.stack.Dup2X1Inst;
import xianzhan.jvmjava.java.instruction.stack.Dup2X2Inst;
import xianzhan.jvmjava.java.instruction.stack.DupInst;
import xianzhan.jvmjava.java.instruction.stack.DupX1Inst;
import xianzhan.jvmjava.java.instruction.stack.DupX2Inst;
import xianzhan.jvmjava.java.instruction.stack.Pop2Inst;
import xianzhan.jvmjava.java.instruction.stack.PopInst;
import xianzhan.jvmjava.java.instruction.stack.SwapInst;
import xianzhan.jvmjava.java.instruction.stores.AAStoreInst;
import xianzhan.jvmjava.java.instruction.stores.AStore0Inst;
import xianzhan.jvmjava.java.instruction.stores.AStore1Inst;
import xianzhan.jvmjava.java.instruction.stores.AStore2Inst;
import xianzhan.jvmjava.java.instruction.stores.AStore3Inst;
import xianzhan.jvmjava.java.instruction.stores.AStoreInst;
import xianzhan.jvmjava.java.instruction.stores.BAStoreInst;
import xianzhan.jvmjava.java.instruction.stores.CAStoreInst;
import xianzhan.jvmjava.java.instruction.stores.DAStoreInst;
import xianzhan.jvmjava.java.instruction.stores.DStore0Inst;
import xianzhan.jvmjava.java.instruction.stores.DStore1Inst;
import xianzhan.jvmjava.java.instruction.stores.DStore2Inst;
import xianzhan.jvmjava.java.instruction.stores.DStore3Inst;
import xianzhan.jvmjava.java.instruction.stores.DStoreInst;
import xianzhan.jvmjava.java.instruction.stores.FAStoreInst;
import xianzhan.jvmjava.java.instruction.stores.FStore0Inst;
import xianzhan.jvmjava.java.instruction.stores.FStore1Inst;
import xianzhan.jvmjava.java.instruction.stores.FStore2Inst;
import xianzhan.jvmjava.java.instruction.stores.FStore3Inst;
import xianzhan.jvmjava.java.instruction.stores.FStoreInst;
import xianzhan.jvmjava.java.instruction.stores.IAStoreInst;
import xianzhan.jvmjava.java.instruction.stores.IStore0Inst;
import xianzhan.jvmjava.java.instruction.stores.IStore1Inst;
import xianzhan.jvmjava.java.instruction.stores.IStore2Inst;
import xianzhan.jvmjava.java.instruction.stores.IStore3Inst;
import xianzhan.jvmjava.java.instruction.stores.IStoreInst;
import xianzhan.jvmjava.java.instruction.stores.LAStoreInst;
import xianzhan.jvmjava.java.instruction.stores.LStore0Inst;
import xianzhan.jvmjava.java.instruction.stores.LStore1Inst;
import xianzhan.jvmjava.java.instruction.stores.LStore2Inst;
import xianzhan.jvmjava.java.instruction.stores.LStore3Inst;
import xianzhan.jvmjava.java.instruction.stores.LStoreInst;
import xianzhan.jvmjava.java.instruction.stores.SAStoreInst;
import xianzhan.jvmjava.java.util.IOUtils;

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

    private static final Instruction nop = new NopInst();

    private static final Instruction aconst_null = new AConstNullInst();

    private static final Instruction iconst_m1 = new IConstM1Inst();
    private static final Instruction iconst_0  = new IConst0Inst();
    private static final Instruction iconst_1  = new IConst1Inst();
    private static final Instruction iconst_2  = new IConst2Inst();
    private static final Instruction iconst_3  = new IConst3Inst();
    private static final Instruction iconst_4  = new IConst4Inst();
    private static final Instruction iconst_5  = new IConst5Inst();

    private static final Instruction lconst_0 = new LConst0Inst();
    private static final Instruction lconst_1 = new LConst1Inst();

    private static final Instruction fconst_0 = new FConst0Inst();
    private static final Instruction fconst_1 = new FConst1Inst();
    private static final Instruction fconst_2 = new FConst2Inst();

    private static final Instruction dconst_0 = new DConst0Inst();
    private static final Instruction dconst_1 = new DConst1Inst();

    private static final Instruction iload_0 = new ILoad0Inst();
    private static final Instruction iload_1 = new ILoad1Inst();
    private static final Instruction iload_2 = new ILoad2Inst();
    private static final Instruction iload_3 = new ILoad3Inst();

    private static final Instruction lload_0 = new LLoad0Inst();
    private static final Instruction lload_1 = new LLoad1Inst();
    private static final Instruction lload_2 = new LLoad2Inst();
    private static final Instruction lload_3 = new LLoad3Inst();

    private static final Instruction fload_0 = new FLoad0Inst();
    private static final Instruction fload_1 = new FLoad1Inst();
    private static final Instruction fload_2 = new FLoad2Inst();
    private static final Instruction fload_3 = new FLoad3Inst();

    private static final Instruction dload_0 = new DLoad0Inst();
    private static final Instruction dload_1 = new DLoad1Inst();
    private static final Instruction dload_2 = new DLoad2Inst();
    private static final Instruction dload_3 = new DLoad3Inst();

    private static final Instruction aload_0 = new ALoad0Inst();
    private static final Instruction aload_1 = new ALoad1Inst();
    private static final Instruction aload_2 = new ALoad2Inst();
    private static final Instruction aload_3 = new ALoad3Inst();

    private static final Instruction iaload = new IALoadInst();
    private static final Instruction laload = new LALoadInst();
    private static final Instruction faload = new FALoadInst();
    private static final Instruction daload = new DALoadInst();
    private static final Instruction aaload = new AALoadInst();
    private static final Instruction baload = new BALoadInst();
    private static final Instruction caload = new CALoadInst();
    private static final Instruction saload = new SALoadInst();

    private static final Instruction istore_0 = new IStore0Inst();
    private static final Instruction istore_1 = new IStore1Inst();
    private static final Instruction istore_2 = new IStore2Inst();
    private static final Instruction istore_3 = new IStore3Inst();

    private static final Instruction lstore_0 = new LStore0Inst();
    private static final Instruction lstore_1 = new LStore1Inst();
    private static final Instruction lstore_2 = new LStore2Inst();
    private static final Instruction lstore_3 = new LStore3Inst();

    private static final Instruction fstore_0 = new FStore0Inst();
    private static final Instruction fstore_1 = new FStore1Inst();
    private static final Instruction fstore_2 = new FStore2Inst();
    private static final Instruction fstore_3 = new FStore3Inst();

    private static final Instruction dstore_0 = new DStore0Inst();
    private static final Instruction dstore_1 = new DStore1Inst();
    private static final Instruction dstore_2 = new DStore2Inst();
    private static final Instruction dstore_3 = new DStore3Inst();

    private static final Instruction astore_0 = new AStore0Inst();
    private static final Instruction astore_1 = new AStore1Inst();
    private static final Instruction astore_2 = new AStore2Inst();
    private static final Instruction astore_3 = new AStore3Inst();

    private static final Instruction iastore = new IAStoreInst();
    private static final Instruction lastore = new LAStoreInst();
    private static final Instruction fastore = new FAStoreInst();
    private static final Instruction dastore = new DAStoreInst();
    private static final Instruction aastore = new AAStoreInst();
    private static final Instruction bastore = new BAStoreInst();
    private static final Instruction castore = new CAStoreInst();
    private static final Instruction sastore = new SAStoreInst();

    private static final Instruction pop     = new PopInst();
    private static final Instruction pop2    = new Pop2Inst();
    private static final Instruction dup     = new DupInst();
    private static final Instruction dup_x1  = new DupX1Inst();
    private static final Instruction dup_x2  = new DupX2Inst();
    private static final Instruction dup2    = new Dup2Inst();
    private static final Instruction dup2_x1 = new Dup2X1Inst();
    private static final Instruction dup2_x2 = new Dup2X2Inst();
    private static final Instruction swap    = new SwapInst();

    private static final Instruction iadd = new IAddInst();
    private static final Instruction ladd = new LAddInst();
    private static final Instruction fadd = new FAddInst();
    private static final Instruction dadd = new DAddInst();

    private static final Instruction isub = new ISubInst();
    private static final Instruction lsub = new LSubInst();
    private static final Instruction fsub = new FSubInst();
    private static final Instruction dsub = new DSubInst();

    private static final Instruction imul = new IMulInst();
    private static final Instruction lmul = new LMulInst();
    private static final Instruction fmul = new FMulInst();
    private static final Instruction dmul = new DMulInst();

    private static final Instruction idiv = new IDivInst();
    private static final Instruction ldiv = new LDivInst();
    private static final Instruction fdiv = new FDivInst();
    private static final Instruction ddiv = new DDivInst();

    private static final Instruction imod = new IRemInst();
    private static final Instruction lmod = new LRemInst();
    private static final Instruction fmod = new FRemInst();
    private static final Instruction dmod = new DRemInst();

    private static final Instruction ineg = new INegInst();
    private static final Instruction lneg = new LNegInst();
    private static final Instruction fneg = new FNegInst();
    private static final Instruction dneg = new DNegInst();

    private static final Instruction ishl  = new IShLInst();
    private static final Instruction lshl  = new LShLInst();
    private static final Instruction ishr  = new IShRInst();
    private static final Instruction lshr  = new LShRInst();
    private static final Instruction iushr = new IUShRInst();
    private static final Instruction lushr = new LUShRInst();

    private static final Instruction iand = new IAndInst();
    private static final Instruction land = new LAndInst();
    private static final Instruction ior  = new IOrInst();
    private static final Instruction lor  = new LOrInst();
    private static final Instruction ixor = new IXOrInst();
    private static final Instruction lxor = new LXOrInst();

    private static final Instruction i2l       = new I2LInst();
    private static final Instruction i2f       = new I2FInst();
    private static final Instruction i2d       = new I2DInst();
    private static final Instruction l2i       = new L2IInst();
    private static final Instruction l2f       = new L2FInst();
    private static final Instruction l2d       = new L2DInst();
    private static final Instruction f2i       = new F2IInst();
    private static final Instruction f2l       = new F2LInst();
    private static final Instruction f2d       = new F2DInst();
    private static final Instruction d2i       = new D2IInst();
    private static final Instruction d2l       = new D2LInst();
    private static final Instruction d2f       = new D2FInst();
    private static final Instruction int2byte  = new Int2ByteInst();
    private static final Instruction int2char  = new Int2CharInst();
    private static final Instruction int2short = new Int2ShortInst();

    private static final Instruction lcmp  = new LCmpInst();
    private static final Instruction fcmpl = new FCmpLInst();
    private static final Instruction fcmpg = new FCmpGInst();
    private static final Instruction dcmpl = new DCmpLInst();
    private static final Instruction dcmpg = new DCmpGInst();

    private static final Instruction ireturn = new IReturnInst();
    private static final Instruction lreturn = new LReturnInst();
    private static final Instruction freturn = new FReturnInst();
    private static final Instruction dreturn = new DReturnInst();
    private static final Instruction areturn = new AReturnInst();
    private static final Instruction return_ = new ReturnInst();

    private static final Instruction arraylength = new ArrayLengthInst();

    private static final Instruction athrow = new AThrowInst();

    private static final Instruction invokenative = new InvokenativeInst();

    // ------ 常量 ------

    public static Instruction read(int opCode, DataInputStream dis, ConstantPool constantPool) throws IOException {
        return switch (opCode) {
            case ByteCodes.nop -> nop;
            case ByteCodes.aconst_null -> aconst_null;
            case ByteCodes.iconst_m1 -> iconst_m1;
            case ByteCodes.iconst_0 -> iconst_0;
            case ByteCodes.iconst_1 -> iconst_1;
            case ByteCodes.iconst_2 -> iconst_2;
            case ByteCodes.iconst_3 -> iconst_3;
            case ByteCodes.iconst_4 -> iconst_4;
            case ByteCodes.iconst_5 -> iconst_5;
            case ByteCodes.lconst_0 -> lconst_0;
            case ByteCodes.lconst_1 -> lconst_1;
            case ByteCodes.fconst_0 -> fconst_0;
            case ByteCodes.fconst_1 -> fconst_1;
            case ByteCodes.fconst_2 -> fconst_2;
            case ByteCodes.dconst_0 -> dconst_0;
            case ByteCodes.dconst_1 -> dconst_1;

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
            case ByteCodes.iload_0 -> iload_0;
            case ByteCodes.iload_1 -> iload_1;
            case ByteCodes.iload_2 -> iload_2;
            case ByteCodes.iload_3 -> iload_3;
            case ByteCodes.lload_0 -> lload_0;
            case ByteCodes.lload_1 -> lload_1;
            case ByteCodes.lload_2 -> lload_2;
            case ByteCodes.lload_3 -> lload_3;
            case ByteCodes.fload_0 -> fload_0;
            case ByteCodes.fload_1 -> fload_1;
            case ByteCodes.fload_2 -> fload_2;
            case ByteCodes.fload_3 -> fload_3;
            case ByteCodes.dload_0 -> dload_0;
            case ByteCodes.dload_1 -> dload_1;
            case ByteCodes.dload_2 -> dload_2;
            case ByteCodes.dload_3 -> dload_3;
            case ByteCodes.aload_0 -> aload_0;
            case ByteCodes.aload_1 -> aload_1;
            case ByteCodes.aload_2 -> aload_2;
            case ByteCodes.aload_3 -> aload_3;
            case ByteCodes.iaload -> iaload;
            case ByteCodes.laload -> laload;
            case ByteCodes.faload -> faload;
            case ByteCodes.daload -> daload;
            case ByteCodes.aaload -> aaload;
            case ByteCodes.baload -> baload;
            case ByteCodes.caload -> caload;
            case ByteCodes.saload -> saload;

            case ByteCodes.istore -> new IStoreInst(dis.readUnsignedByte());
            case ByteCodes.lstore -> new LStoreInst(dis.readUnsignedByte());
            case ByteCodes.fstore -> new FStoreInst(dis.readUnsignedByte());
            case ByteCodes.dstore -> new DStoreInst(dis.readUnsignedByte());
            case ByteCodes.astore -> new AStoreInst(dis.readUnsignedByte());
            case ByteCodes.istore_0 -> istore_0;
            case ByteCodes.istore_1 -> istore_1;
            case ByteCodes.istore_2 -> istore_2;
            case ByteCodes.istore_3 -> istore_3;
            case ByteCodes.lstore_0 -> lstore_0;
            case ByteCodes.lstore_1 -> lstore_1;
            case ByteCodes.lstore_2 -> lstore_2;
            case ByteCodes.lstore_3 -> lstore_3;
            case ByteCodes.fstore_0 -> fstore_0;
            case ByteCodes.fstore_1 -> fstore_1;
            case ByteCodes.fstore_2 -> fstore_2;
            case ByteCodes.fstore_3 -> fstore_3;
            case ByteCodes.dstore_0 -> dstore_0;
            case ByteCodes.dstore_1 -> dstore_1;
            case ByteCodes.dstore_2 -> dstore_2;
            case ByteCodes.dstore_3 -> dstore_3;
            case ByteCodes.astore_0 -> astore_0;
            case ByteCodes.astore_1 -> astore_1;
            case ByteCodes.astore_2 -> astore_2;
            case ByteCodes.astore_3 -> astore_3;
            case ByteCodes.iastore -> iastore;
            case ByteCodes.lastore -> lastore;
            case ByteCodes.fastore -> fastore;
            case ByteCodes.dastore -> dastore;
            case ByteCodes.aastore -> aastore;
            case ByteCodes.bastore -> bastore;
            case ByteCodes.castore -> castore;
            case ByteCodes.sastore -> sastore;

            case ByteCodes.pop -> pop;
            case ByteCodes.pop2 -> pop2;
            case ByteCodes.dup -> dup;
            case ByteCodes.dup_x1 -> dup_x1;
            case ByteCodes.dup_x2 -> dup_x2;
            case ByteCodes.dup2 -> dup2;
            case ByteCodes.dup2_x1 -> dup2_x1;
            case ByteCodes.dup2_x2 -> dup2_x2;
            case ByteCodes.swap -> swap;

            case ByteCodes.iadd -> iadd;
            case ByteCodes.ladd -> ladd;
            case ByteCodes.fadd -> fadd;
            case ByteCodes.dadd -> dadd;

            case ByteCodes.isub -> isub;
            case ByteCodes.lsub -> lsub;
            case ByteCodes.fsub -> fsub;
            case ByteCodes.dsub -> dsub;

            case ByteCodes.imul -> imul;
            case ByteCodes.lmul -> lmul;
            case ByteCodes.fmul -> fmul;
            case ByteCodes.dmul -> dmul;

            case ByteCodes.idiv -> idiv;
            case ByteCodes.ldiv -> ldiv;
            case ByteCodes.fdiv -> fdiv;
            case ByteCodes.ddiv -> ddiv;

            case ByteCodes.imod -> imod;
            case ByteCodes.lmod -> lmod;
            case ByteCodes.fmod -> fmod;
            case ByteCodes.dmod -> dmod;

            case ByteCodes.ineg -> ineg;
            case ByteCodes.lneg -> lneg;
            case ByteCodes.fneg -> fneg;
            case ByteCodes.dneg -> dneg;

            case ByteCodes.ishl -> ishl;
            case ByteCodes.lshl -> lshl;
            case ByteCodes.ishr -> ishr;
            case ByteCodes.lshr -> lshr;
            case ByteCodes.iushr -> iushr;
            case ByteCodes.lushr -> lushr;

            case ByteCodes.iand -> iand;
            case ByteCodes.land -> land;
            case ByteCodes.ior -> ior;
            case ByteCodes.lor -> lor;
            case ByteCodes.ixor -> ixor;
            case ByteCodes.lxor -> lxor;

            case ByteCodes.iinc -> new IIncInst(dis.readUnsignedByte(), dis.readByte());

            case ByteCodes.i2l -> i2l;
            case ByteCodes.i2f -> i2f;
            case ByteCodes.i2d -> i2d;
            case ByteCodes.l2i -> l2i;
            case ByteCodes.l2f -> l2f;
            case ByteCodes.l2d -> l2d;
            case ByteCodes.f2i -> f2i;
            case ByteCodes.f2l -> f2l;
            case ByteCodes.f2d -> f2d;
            case ByteCodes.d2i -> d2i;
            case ByteCodes.d2l -> d2l;
            case ByteCodes.d2f -> d2f;
            case ByteCodes.int2byte -> int2byte;
            case ByteCodes.int2char -> int2char;
            case ByteCodes.int2short -> int2short;

            case ByteCodes.lcmp -> lcmp;
            case ByteCodes.fcmpl -> fcmpl;
            case ByteCodes.fcmpg -> fcmpg;
            case ByteCodes.dcmpl -> dcmpl;
            case ByteCodes.dcmpg -> dcmpg;

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

            case ByteCodes.ireturn -> ireturn;
            case ByteCodes.lreturn -> lreturn;
            case ByteCodes.freturn -> freturn;
            case ByteCodes.dreturn -> dreturn;
            case ByteCodes.areturn -> areturn;
            case ByteCodes.return_ -> return_;

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
            case ByteCodes.newarray -> new NewArrayInst(dis.readUnsignedByte());
            case ByteCodes.anewarray -> new ANewArrayInst(dis.readUnsignedShort());
            case ByteCodes.arraylength -> arraylength;

            case ByteCodes.athrow -> athrow;

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
                    case ByteCodes.ret -> throw new UnsupportedOperationException("ret");
                    default -> throw new UnsupportedOperationException("wide op " + wideOpcode);
                };
            }

            case ByteCodes.multianewarray -> new MultiANewArrayInst(dis.readUnsignedShort(), dis.readUnsignedByte());
            case ByteCodes.if_acmp_null -> new IfNullInst(dis.readShort());
            case ByteCodes.if_acmp_nonnull -> new IfNonNullInst(dis.readShort());
            case ByteCodes.goto_w -> new GotoWInst(dis.readInt());
            // jsr_w, 同 jsr, 忽略
            case ByteCodes.jsr_w -> throw new UnsupportedOperationException("jsr_w");

            case ByteCodes.invokenative -> invokenative;

            default -> null;
        };
    }

    public static Instruction read(int opCode) {
        try {
            return read(opCode, null, null);
        } catch (java.lang.Exception e) {
            throw new RuntimeException(e);
        }
    }
}
