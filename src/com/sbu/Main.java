package com.sbu;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    static String[] get_address(String s1){
        String[] s2=s1.split("\\(");
        String[] s3=s2[1].split("\\)");

        String offset=s2[0];
        String first_inx=s3[0];

        String[] out=new String[2];
        out[0]=offset;
        out[1]=first_inx;

        return out;
    }
    static int getKey(Map<Integer, String> map, String value) {
        for (Map.Entry<Integer, String> entry: map.entrySet())
        {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return -1;
    }

    static void R_type_Assembler(int rd,     int rs,     int rt,   int sha,    int func){
        ArrayList<String> array=new ArrayList<>();
        String opcode="000000";
        array.add(opcode);


        String source=Integer.toBinaryString(rs);
        if(source.length()<5){
            StringBuilder temp= new StringBuilder();
            for(int i=0;i<5-source.length();i++)
                temp.append("0");
            array.add(temp+source);
        }
        else
            array.add(source);

        String temporary=Integer.toBinaryString(rt);
        if(temporary.length()<5){
            StringBuilder temp= new StringBuilder();
            for(int i=0;i<5-temporary.length();i++)
                temp.append("0");
            array.add(temp+temporary);
        }
        else
            array.add(temporary);

        String destination=Integer.toBinaryString(rd);
        if(destination.length()<5){
            StringBuilder temp = new StringBuilder();
            for(int i=0;i<5-destination.length();i++)
                temp.append("0");
            array.add(temp+destination);
        }
        else
            array.add(destination);

        String shift=Integer.toBinaryString(sha);
        if(shift.length()<5){
            StringBuilder temp= new StringBuilder();
            for(int i=0;i<5-shift.length();i++)
                temp.append("0");
            array.add(temp+shift);
        }
        else
            array.add(shift);


        String function=Integer.toBinaryString(func);
        if(function.length()<6){
            StringBuilder temp= new StringBuilder();
            for(int i=0;i<6-function.length();i++)
                temp.append("0");
            array.add(temp+function);
        }
        else
            array.add(function);

        String output = new String();
        for(int i=0;i<array.size();i++){
            output+=array.get(i);
        }

        int dec=new BigInteger(output,2).intValue();
        String ss=Integer.toHexString(dec);
        if(ss.length()<8) {
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < 8 - ss.length(); i++)
                temp.append("0");
            ss = temp + ss;
        }
        String hex_opc="0x"+ss;

        System.out.println(hex_opc);
        System.out.println("R-Type");
        System.out.println("op: "+array.get(0));
        System.out.println("rs: "+array.get(1));
        System.out.println("rt: "+array.get(2));
        System.out.println("rd: "+array.get(3));
        System.out.println("shamt: "+array.get(4));
        System.out.println("funct: "+array.get(5));
    }
    static void I_type_Assembler(int op,     int rs,     int rt,   int im){
        ArrayList<String> array=new ArrayList<>();

        String opcode=Integer.toBinaryString(op);
        if(opcode.length()<6){
            StringBuilder temp= new StringBuilder();
            for(int i=0;i<6-opcode.length();i++)
                temp.append("0");
            array.add(temp+opcode);
        }
        else
            array.add(opcode);


        String source=Integer.toBinaryString(rs);
        if(source.length()<5){
            StringBuilder temp= new StringBuilder();
            for(int i=0;i<5-source.length();i++)
                temp.append("0");
            array.add(temp+source);
        }
        else
            array.add(source);

        String temporary=Integer.toBinaryString(rt);
        if(temporary.length()<5){
            StringBuilder temp= new StringBuilder();
            for(int i=0;i<5-temporary.length();i++)
                temp.append("0");
            array.add(temp+temporary);
        }
        else
            array.add(temporary);

        String immediate=Integer.toBinaryString(im);
        if(immediate.length()<16){
            StringBuilder temp= new StringBuilder();
            for(int i=0;i<16-immediate.length();i++)
                temp.append("0");
            array.add(temp+immediate);
        }
        else
            array.add(immediate);


        String output = new String();
        for(int i=0;i<array.size();i++){
            output+=array.get(i);
        }

        int dec=new BigInteger(output,2).intValue();
        String ss=Integer.toHexString(dec);
        if(ss.length()<8) {
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < 8 - ss.length(); i++)
                temp.append("0");
            ss = temp + ss;
        }
        String hex_opc="0x"+ss;

        System.out.println(hex_opc);
        System.out.println("I-Type");
        System.out.println("op: "+array.get(0));
        System.out.println("rs: "+array.get(1));
        System.out.println("rt: "+array.get(2));
        System.out.println("constant or address: "+array.get(3));
    }
    static void j_type_Assembler(int op,    int im){
        ArrayList<String> array=new ArrayList<>();

        String opcode=Integer.toBinaryString(op);
        if(opcode.length()<6){
            StringBuilder temp= new StringBuilder();
            for(int i=0;i<6-opcode.length();i++)
                temp.append("0");
            array.add(temp+opcode);
        }
        else
            array.add(opcode);

        String immediate=Integer.toBinaryString(im);
        if(immediate.length()<26){
            StringBuilder temp= new StringBuilder();
            for(int i=0;i<26-immediate.length();i++)
                temp.append("0");
            array.add(temp+immediate);
        }
        else
            array.add(immediate);


        String output = new String();
        for(int i=0;i<array.size();i++){
            output+=array.get(i);
        }

        int dec=new BigInteger(output,2).intValue();
        String ss=Integer.toHexString(dec);
        if(ss.length()<8) {
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < 8 - ss.length(); i++)
                temp.append("0");
            ss = temp + ss;
        }
        String hex_opc="0x"+ss;

        System.out.println(hex_opc);
        System.out.println("J-Type");
        System.out.println("op: "+array.get(0));
        System.out.println("address: "+array.get(1));
    }

    static void R_type_diassembler(int rd,     int rs,     int rt,   int sha,    int func   ,   Map<Integer,String> r_map,  Map<Integer,String> reg_map){
        ArrayList<String> array=new ArrayList<>();
        String opcode="000000";
        array.add(opcode);


        String source=Integer.toBinaryString(rs);
        if(source.length()<5){
            StringBuilder temp= new StringBuilder();
            for(int i=0;i<5-source.length();i++)
                temp.append("0");
            array.add(temp+source);
        }
        else
            array.add(source);

        String temporary=Integer.toBinaryString(rt);
        if(temporary.length()<5){
            StringBuilder temp= new StringBuilder();
            for(int i=0;i<5-temporary.length();i++)
                temp.append("0");
            array.add(temp+temporary);
        }
        else
            array.add(temporary);

        String destination=Integer.toBinaryString(rd);
        if(destination.length()<5){
            StringBuilder temp = new StringBuilder();
            for(int i=0;i<5-destination.length();i++)
                temp.append("0");
            array.add(temp+destination);
        }
        else
            array.add(destination);

        String shift=Integer.toBinaryString(sha);
        if(shift.length()<5){
            StringBuilder temp= new StringBuilder();
            for(int i=0;i<5-shift.length();i++)
                temp.append("0");
            array.add(temp+shift);
        }
        else
            array.add(shift);


        String function=Integer.toBinaryString(func);
        if(function.length()<6){
            StringBuilder temp= new StringBuilder();
            for(int i=0;i<6-function.length();i++)
                temp.append("0");
            array.add(temp+function);
        }
        else
            array.add(function);


        if(func==12 || func==13)
            System.out.println(r_map.get(func));
        else if(func==8 || func==17 || func==19)
            System.out.println(r_map.get(func)+" "+reg_map.get(rs));
        else if(func==16 || func==18)
            System.out.println(r_map.get(func)+" "+reg_map.get(rd));
        else if(func>=24 && func<=27)
            System.out.println(r_map.get(func)+" "+reg_map.get(rs)+","+reg_map.get(rt));
        else if(func==9)
            System.out.println(r_map.get(func)+" "+reg_map.get(rd)+","+reg_map.get(rs));
        else
            System.out.println(r_map.get(func)+" "+reg_map.get(rd)+","+reg_map.get(rs)+","+reg_map.get(rt));

        System.out.println("R-Type");
        System.out.println("op: "+array.get(0));
        System.out.println("rs: "+array.get(1));
        System.out.println("rt: "+array.get(2));
        System.out.println("rd: "+array.get(3));
        System.out.println("shamt: "+array.get(4));
        System.out.println("funct: "+array.get(5));
    }
    static void I_type_diassembler(int op,     int rs,     int rt,   int im,    Map<Integer,String> i_map,  Map<Integer,String> reg_map){
        ArrayList<String> array=new ArrayList<>();

        String opcode=Integer.toBinaryString(op);
        if(opcode.length()<6){
            StringBuilder temp= new StringBuilder();
            for(int i=0;i<6-opcode.length();i++)
                temp.append("0");
            array.add(temp+opcode);
        }
        else
            array.add(opcode);


        String source=Integer.toBinaryString(rs);
        if(source.length()<5){
            StringBuilder temp= new StringBuilder();
            for(int i=0;i<5-source.length();i++)
                temp.append("0");
            array.add(temp+source);
        }
        else
            array.add(source);

        String temporary=Integer.toBinaryString(rt);
        if(temporary.length()<5){
            StringBuilder temp= new StringBuilder();
            for(int i=0;i<5-temporary.length();i++)
                temp.append("0");
            array.add(temp+temporary);
        }
        else
            array.add(temporary);

        String immediate=Integer.toBinaryString(im);
        if(immediate.length()<16){
            StringBuilder temp= new StringBuilder();
            for(int i=0;i<16-immediate.length();i++)
                temp.append("0");
            array.add(temp+immediate);
        }
        else
            array.add(immediate);


        if(op==32 || op==33 || op==35 || op==36 || op==37 || op==40 || op==41 || op==43 || op==49 || op==57)
            System.out.println(i_map.get(op)+" "+reg_map.get(rt)+",0x"+Integer.toHexString(im)+"("+reg_map.get(rs)+")");
        else if(op==1 || op==6 || op==7)
            System.out.println(i_map.get(op)+" "+reg_map.get(rs)+",0x"+Integer.toHexString(im));
        else if(op==15)
            System.out.println(i_map.get(op)+" "+reg_map.get(rt)+",0x"+Integer.toHexString(im));
        else
            System.out.println(i_map.get(op)+" "+reg_map.get(rt)+","+reg_map.get(rs)+",0x"+Integer.toHexString(im));

        System.out.println("I-Type");
        System.out.println("op: "+array.get(0));
        System.out.println("rs: "+array.get(1));
        System.out.println("rt: "+array.get(2));
        System.out.println("constant or address: "+array.get(3));
    }
    static void j_type_diassembler(int op,    int im){
        ArrayList<String> array=new ArrayList<>();

        String opcode=new String();
        if(op==0)
            opcode="000010";
        else
            opcode="000011";
        array.add(opcode);

        String immediate=Integer.toBinaryString(im);
        if(immediate.length()<26){
            StringBuilder temp= new StringBuilder();
            for(int i=0;i<26-immediate.length();i++)
                temp.append("0");
            array.add(temp+immediate);
        }
        else
            array.add(immediate);

        if(op==0)
            System.out.println("j"+" 0x"+Integer.toHexString(im));
        else
            System.out.println("jal"+" 0x"+Integer.toHexString(im));

        System.out.println("J-Type");
        System.out.println("op: "+array.get(0));
        System.out.println("address: "+array.get(1));
    }

    static Map<Integer,String> get_Rtype_map(){
        Map<Integer,String> R_map=new HashMap<>();

        R_map.put(32,"add");
        R_map.put(33,"addu");
        R_map.put(36,"and");
        R_map.put(13,"break");
        R_map.put(26,"div");
        R_map.put(27,"divu");
        R_map.put(9,"jalr");
        R_map.put(8,"jr");
        R_map.put(16,"mfhi");
        R_map.put(18,"mflo");
        R_map.put(17,"mthi");
        R_map.put(19,"mtlo");
        R_map.put(24,"mult");
        R_map.put(25,"multu");
        R_map.put(39,"nor");
        R_map.put(37,"or");
        R_map.put(0,"sll");
        R_map.put(4,"sllv");
        R_map.put(42,"slt");
        R_map.put(43,"sltu");
        R_map.put(3,"sra");
        R_map.put(7,"srav");
        R_map.put(2,"srl");
        R_map.put(6,"srlv");
        R_map.put(34,"sub");
        R_map.put(35,"subu");
        R_map.put(38,"xor");
        R_map.put(12,"syscall");

        return R_map;

    }
    static Map<Integer,String> get_Itype_map(){
        Map<Integer,String> I_map=new HashMap<>();

        I_map.put(8,"addi");
        I_map.put(9,"addiu");
        I_map.put(12,"andi");
        I_map.put(4,"beq");
        I_map.put(1,"bgez");
        I_map.put(7,"bgtz");
        I_map.put(6,"blez");
        I_map.put(5,"bne");
        I_map.put(32,"lb");
        I_map.put(36,"lbu");
        I_map.put(33,"lh");
        I_map.put(37,"lhu");
        I_map.put(15,"lui");
        I_map.put(35,"lw");
        I_map.put(49,"lwc1");
        I_map.put(13,"ori");
        I_map.put(40,"sb");
        I_map.put(10,"slti");
        I_map.put(11,"sltiu");
        I_map.put(41,"sh");
        I_map.put(43,"sw");
        I_map.put(57,"swc1");
        I_map.put(14,"xori");

        return I_map;

    }
    static Map<Integer,String> get_registers_map(){
        Map<Integer,String> regs_map=new HashMap<>();

        regs_map.put(0,"$zero");
        regs_map.put(1,"$at");
        regs_map.put(2,"$v0");
        regs_map.put(3,"$v1");
        regs_map.put(4,"$a0");
        regs_map.put(5,"$a1");
        regs_map.put(6,"$a2");
        regs_map.put(7,"$a3");
        regs_map.put(8,"$t0");
        regs_map.put(9,"$t1");
        regs_map.put(10,"$t2");
        regs_map.put(11,"$t3");
        regs_map.put(12,"$t4");
        regs_map.put(13,"$t5");
        regs_map.put(14,"$t6");
        regs_map.put(15,"$t7");
        regs_map.put(16,"$s0");
        regs_map.put(17,"$s1");
        regs_map.put(18,"$s2");
        regs_map.put(19,"$s3");
        regs_map.put(20,"$s4");
        regs_map.put(21,"$s5");
        regs_map.put(22,"$s6");
        regs_map.put(23,"$s7");
        regs_map.put(24,"$t8");
        regs_map.put(25,"$t9");
        regs_map.put(26,"$k0");
        regs_map.put(27,"$k1");
        regs_map.put(28,"$gp");
        regs_map.put(29,"$sp");
        regs_map.put(30,"$fp");
        regs_map.put(31,"$ra");


        return regs_map;

    }

    public static void main(String[] args) {
        Map<Integer,String> R_map=get_Rtype_map();
        Map<Integer,String> I_map=get_Itype_map();
        Map<Integer,String> regs_map=get_registers_map();

        Scanner sc=new Scanner(System.in);
        String mode =sc.nextLine();

        if(mode.equals("Assembler")){
            String input=sc.nextLine();
            String[] in_temp=input.split(" ");
            String type=in_temp[0];


            if(getKey(R_map,type)!=-1){
                int func=getKey(R_map,type);

                if(func==4 || func==6 || func==7 || (func>=32 && func<=39) || func==42 || func==43){

                    String[] in2=in_temp[1].split(",");
                    int rd=getKey(regs_map,in2[0]);
                    int rs=getKey(regs_map,in2[1]);
                    int rt=getKey(regs_map,in2[2]);

                    R_type_Assembler(rd,        rs,     rt,     0,   func);

                }

                else if((func>=24 && func<=27)){

                    String[] in2=in_temp[1].split(",");
                    int rs=getKey(regs_map,in2[0]);
                    int rt=getKey(regs_map,in2[1]);

                    R_type_Assembler(0,      rs,     rt,     0      ,func);
                }

                else if(func==9){

                    String[] in2=in_temp[1].split(",");
                    int rd=getKey(regs_map,in2[0]);
                    int rs=getKey(regs_map,in2[1]);

                    R_type_Assembler(rd,        rs,     0,     0,      func);
                }


                else if(func==16 || func==18){
                    int rd=getKey(regs_map,in_temp[1]);

                    R_type_Assembler(rd,    0,      0,      0,      func);

                }

                else if(func==8 || func==17 || func==19){
                    int rs=getKey(regs_map,in_temp[1]);

                    R_type_Assembler(0,    rs,      0,      0,      func);

                }

                else if(func==12 || func==13){
                    R_type_Assembler(0,    0,      0,      0,      func);

                }

                else if(func==0 || func==2 || func==3){

                    String[] in2=in_temp[1].split(",");
                    int rd=getKey(regs_map,in2[0]);
                    int rt=getKey(regs_map,in2[1]);
                    int shamt=Integer.parseInt(in2[2]);

                    R_type_Assembler(rd,    0,        rt,        shamt,       func);

                }



            }

            else if(getKey(I_map,type)!=-1){
                int op=getKey(I_map,type);

                if(op>=8 && op<=14){

                    String[] in2=in_temp[1].split(",");
                    int rt=getKey(regs_map,in2[0]);
                    int rs=getKey(regs_map,in2[1]);
                    int imm=new BigInteger(in2[2].substring(2),16).intValue();

                    I_type_Assembler(op,        rs,      rt,         imm);

                }

                else if(op==6 || op==7 || type.equals("bltz")){

                    String[] in2=in_temp[1].split(",");
                    int rs=getKey(regs_map,in2[0]);
                    String label=in2[1];
                    int imm=new BigInteger(label.substring(2),16).intValue();

                    I_type_Assembler(op,        rs,     0,        imm);
                }

                else if(op==1){
                    String[] in2=in_temp[1].split(",");
                    int rs=getKey(regs_map,in2[0]);
                    String label=in2[1];
                    int imm=new BigInteger(label.substring(2),16).intValue();

                    I_type_Assembler(op,        rs,     1,        imm);
                }

                else if(op==4 || op==5){

                    String[] in2=in_temp[1].split(",");
                    int rs=getKey(regs_map,in2[0]);
                    int rt=getKey(regs_map,in2[1]);
                    String label=in2[2];
                    int imm=new BigInteger(label.substring(2),16).intValue();

                    I_type_Assembler(5,     rs,     rt,           imm);
                }

                else if(op==32 || op==33 || (op>=35 && op<=37) || op==40 || op==41 || op==43 || op==49 || op==57){

                    String[] in2=in_temp[1].split(",");
                    int rt=getKey(regs_map,in2[0]);
                    String address_temp=in2[1];
                    String[] address=get_address(address_temp);
                    int rs=getKey(regs_map,address[1]);
                    int imm=new BigInteger(address[0].substring(2),16).intValue();

                    I_type_Assembler(op,        rs,         rt,        imm);
                }

                else if(op==15){

                    String[] in2=in_temp[1].split(",");
                    int rt=getKey(regs_map,in2[0]);
                    int imm=new BigInteger(in2[1].substring(2),16).intValue();

                    I_type_Assembler(op,        0,       rt,         imm);
                }






            }

            else{
                if(type.equals("j")){

                    int imm=new BigInteger(in_temp[1].substring(2),16).intValue();

                    j_type_Assembler(2,imm);
                }

                else if(type.equals("jal")){

                    int imm=new BigInteger(in_temp[1].substring(2),16).intValue();

                    j_type_Assembler(3,imm);

                }


        }

        }
        else{
            String in=sc.nextLine();
            String hex=in.substring(2);
            String binary=new BigInteger(hex,16).toString(2);

            if(binary.length()<32){
                StringBuilder temp= new StringBuilder();
                for(int i=0;i<32-binary.length();i++)
                    temp.append("0");
                binary=temp+binary;
            }

            String opcode=binary.substring(0,6);

            if(opcode.equals("000000")) {
                int rs = Integer.parseInt( binary.substring(6,11) ,2);
                int rt = Integer.parseInt( binary.substring(11,16) ,2);
                int rd = Integer.parseInt( binary.substring(16,21) ,2);
                int shamt = Integer.parseInt( binary.substring(21,26) ,2);
                int func = Integer.parseInt( binary.substring(26) ,2);

                R_type_diassembler(rd,    rs,     rt,     shamt,      func,     R_map,      regs_map);


            }
            else if(opcode.equals("000010")){
                int im=Integer.parseInt( binary.substring(6) ,2);

                j_type_diassembler(0,     im);
            }
            else if(opcode.equals("000011")){
                int im=Integer.parseInt( binary.substring(6) ,2);

                j_type_diassembler(1,     im);

            }

            else {
                int rs = Integer.parseInt( binary.substring(6,11) ,2);
                int rt = Integer.parseInt( binary.substring(11,16) ,2);
                int imm = Integer.parseInt( binary.substring(16) ,2);


                I_type_diassembler(Integer.parseInt(opcode,2),    rs,     rt,     imm,     I_map,      regs_map);

            }








        }


    }
}
