package com.github.gfx.asmexample

import org.objectweb.asm.*

public class ClassDumper extends ClassVisitor {

    public static void dump(ArrayList<String> args) throws IOException {
        ClassVisitor visitor = new ClassDumper(Opcodes.ASM5);

        for (String file : args) {
            InputStream ins = new FileInputStream(file);

            new ClassReader(ins).accept(visitor, 0);
        }
    }

    public ClassDumper(int v) {
        super(v);
    }

    @Override
    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces) {
        System.out.println("Visiting class: " + name);
        System.out.println("Class Major Version: " + version);
        System.out.println("Super class: " + superName);
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public void visitOuterClass(String owner, String name, String desc) {
        System.out.println("Outer class: " + owner);
        super.visitOuterClass(owner, name, desc);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc,
                                             boolean visible) {
        System.out.println("Annotation: " + desc);
        return super.visitAnnotation(desc, visible);
    }

    @Override
    public void visitAttribute(Attribute attr) {
        System.out.println("Class Attribute: " + attr.type);
        super.visitAttribute(attr);
    }

    @Override
    public void visitInnerClass(String name, String outerName,
                                String innerName, int access) {
        System.out.println("Inner Class: " + innerName + " defined in " + outerName);
        super.visitInnerClass(name, outerName, innerName, access);
    }

    @Override
    public FieldVisitor visitField(int access, String name,
                                   String desc, String signature, Object value) {
        System.out.println("Field: " + name + " " + desc + " value:" + value);
        return super.visitField(access, name, desc, signature, value);
    }

    @Override
    public void visitEnd() {
        System.out.println("Ends here");
        super.visitEnd();
    }

    @Override
    public MethodVisitor visitMethod(int access, String name,
                                     String desc, String signature, String[] exceptions) {
        System.out.println("Method: " + name + " " + desc);
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

    @Override
    public void visitSource(String source, String debug) {
        System.out.println("Source: " + source);
        super.visitSource(source, debug);
    }
}
