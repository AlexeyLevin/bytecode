package branching.dobuleb;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Label;

import java.io.FileOutputStream;

public class ClassGen {

    public static void main(final String... args) throws Exception {
        final String path = args[0];
        final byte[] byteCode = new ClassGen().generateMathClass();
        try (FileOutputStream stream = new FileOutputStream(path)) {
            stream.write(byteCode);
        }
    }

    private byte[] generateMathClass() {
        final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(51,
                Opcodes.ACC_PUBLIC,
                "Math",
                null,
                "java/lang/Object",
                null);
        generateDefaultConstructor(cw);
        generateDMethod(cw);
        generateAbsMethod(cw);
        cw.visitEnd();
        return cw.toByteArray();
    }

    private void generateDefaultConstructor(final ClassWriter cw) {
        final MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }

    private void generateDMethod(final ClassWriter cw) {
        final MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                "d", // method name
                "(DDD)D", // method descriptor
                null,    // exceptions
                null);   // method attributes
        mv.visitCode();
        
        //b * b store to b
        mv.visitVarInsn(Opcodes.DLOAD, 2);
        mv.visitVarInsn(Opcodes.DLOAD, 2);
        mv.visitInsn(Opcodes.DMUL);
        mv.visitVarInsn(Opcodes.DSTORE, 2);

        //4 * a store to a
        mv.visitLdcInsn(4.0);
        mv.visitVarInsn(Opcodes.DLOAD, 0);
        mv.visitInsn(Opcodes.DMUL);
        mv.visitVarInsn(Opcodes.DSTORE, 0);

        // result in a * c store to c
        mv.visitVarInsn(Opcodes.DLOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 4);
        mv.visitInsn(Opcodes.DMUL);
        mv.visitVarInsn(Opcodes.DSTORE, 4);

        //b - c
        mv.visitVarInsn(Opcodes.DLOAD, 2);
        mv.visitVarInsn(Opcodes.DLOAD, 4);
        mv.visitInsn(Opcodes.DSUB);

        //return result from stack
        mv.visitInsn(Opcodes.DRETURN);
        /*public static double d(final double a, final double b, final double c) {
                return b * b - 4 * a * c;
        }*/
        mv.visitMaxs(4, 6);
        mv.visitEnd();
    }

    private void generateAbsMethod(final ClassWriter cw) {
        final MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                "abs",    // method name
                "(D)D", // method descriptor
                null,     // exceptions
                null);    // method attributes
        mv.visitCode();
        final Label elseLable = new Label();

        mv.visitInsn(Opcodes.DCONST_0);
        mv.visitVarInsn(Opcodes.DLOAD, 0);
        mv.visitInsn(Opcodes.DCMPG);
        mv.visitJumpInsn(Opcodes.IFGE, elseLable);
        mv.visitVarInsn(Opcodes.DLOAD, 0);
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitLabel(elseLable);
        mv.visitVarInsn(Opcodes.DLOAD, 0);
        mv.visitInsn(Opcodes.DNEG);
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(2, 2);
        /*
        public static double abs(final double a) {
            if (a >= 0) {
            return a;
            } else {
            return -a;
            }
        }
        */
        mv.visitEnd();
    }


}
