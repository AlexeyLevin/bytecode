package branching;

/*
        final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(51,
        Opcodes.ACC_PUBLIC,     // флаги (атрибуты) класса
        "Summator",             // имя класса
        null,
        "java/lang/Object",     // класс, от которого наследуемся
        null);
        generateDefaultConstructor(cw); // генерируем байт-код конструктора по умолчанию
        generateSummMethod(cw);         // генерируем метод sum
        cw.visitEnd();                  // заканчиваем генерацию класса
        return cw.toByteArray();        // возвращаем массив, который содержит байт код
        

        Давайте теперь рассмотрим метод, который генерирует этот самый конструктор:

        final MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, // флаги (атрибуты) метода, который мы генерируем
        "<init>", // имя метода(конструктор); 
        "()V", // тип возвращаемых данных; конструктор ничего не возвращает.
        null, null);
        mv.visitCode();
        
        метод visitVarInsn используется для добавления инструкции, которая работает с локальными переменными.
        К таким инструкциям относятся: ILOAD, LLOAD, FLOAD, DLOAD, ALOAD, ISTORE, LSTORE, FSTORE, DSTORE, ASTORE, RET
        Первым аргументом передается инструкция, а вторым номер переменной, над которой нужно выполнить инструкцию.
        
        mv.visitVarInsn(Opcodes.ALOAD, 0); // добавляем в стек локальную переменную 0 (это указатель на this).
        
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V"); // вызывает конструктор класса предка
        
        метод visitInsn используется для генерации инструкций байт-кода, которые не требуют никаких аргументов на вход.
        К ним относятся: IRETURN/RETURN и многие другие.
        
        mv.visitInsn(Opcodes.RETURN); // заканчиваем метод
        
        mv.visitMaxs(1, 1); // устанавливаем размер стека в 1 и количество локальных перменных 1
        mv.visitEnd();
*
* */
