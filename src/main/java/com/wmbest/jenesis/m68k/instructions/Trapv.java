package com.wmbest.jenesis.m68k.instructions;

import com.wmbest.jenesis.m68k.*;
import com.wmbest.jenesis.util.*;

public class Trapv extends SystemInstruction {
    @Override
    public void handle() {
        if (cpu.V()) {
            Trap t = new Trap();
            t.setup(7);
            t.call();
        }
    }
}

