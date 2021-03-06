package com.wmbest.jenesis.m68k.instructions;

public class CMP extends SEAInstruction {

    public static CMP getInstruction(int value) {
        int mode = (value >> 6) & 0x7;

        if ((mode & 0x3) == 0x3) {
            // return new CmpA();
        }

        if ((mode >> 2) == 1) {
            int m = (value >> 3) & 0x7;
            if (m == 0x1) {
                //return new CmpM();
            }
            //return new Eor();
        }

        return new CMP();
    }
    
    @Override
    public void setup(int value) {
        super.setup(value);
        name = "CMP";
        size = getSize((value >> 6) & 0x7);
        operands[0].size = size;
    }

    public int getSize(int size) {
        switch(size) {
            case 0:
                return BYTE;
            case 1:
                return WORD;
            case 2:
                return LONG;
        }
        return -1;
    }

    @Override
    public void handle() {
        int dx = (value >> 9) & 0x7;
        long val = cpu.getDx(dx) - operands[0].getVal();
        if (val < 0) {
            cpu.setN(true);
            cpu.setV(cpu.C() ^ true);
            cpu.setC(true);
        } else if (val == 0) {
            cpu.setZ(true);
        }
    }

    @Override
    public String disassemble() {
        int dx = (value >> 9) & 0x7;
        return "CMP" + SIZE_ABBVR[size] + "\t" + operands[0].toString() + ", D" + dx;
    }
}
