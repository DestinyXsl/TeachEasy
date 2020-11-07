package com.homework.teach.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class readTxtUtil {
    /** * 读取文件指定行。 */
//    public static void main(String[] args) throws IOException {
//        // 指定读取的行号
//        int lineNumber = 3000000;
//        // 读取文件
////        File sourceFile = new File("src/main/java/demo/ReadSelectedLine.java");
//        File sourceFile = new File("C:\\Users\\xsl\\Desktop\\asdf.txt");
//        // 读取指定的行
//        for(int i= 0 ;i<10000;i++){
//            readAppointedLineNumber(sourceFile, lineNumber+i);
//        }
//        // 获取文件的内容的总行数
//        System.out.println("总行数：" + getTotalLines(sourceFile));
//    }
//    public static void main(String[] args) throws Exception {
//        File sourceFile = new File("C:\\Users\\xsl\\Desktop\\asdf.txt");
//        FileReader in = new FileReader(sourceFile);
//        LineNumberReader reader = new LineNumberReader(in);
//        String s = null;
//        int line = 1000000;
//        int total = getTotalLines(sourceFile);
//        int count = 0;
//        for (int i = 0;i<1000000;i++){
//            reader.setLineNumber(line+i);
//            s = reader.readLine();
//            System.out.println(s);
////            String[] dd = s.split(" \\| ",11);
////            if (dd[8].equals("00054") && (dd[9].equals("0000000001") || dd[9].equals("0000000002"))){
////                count++;
////                System.out.println(i+":::"+s);
////            }
//        }
//        System.out.println(count);
////        String[] dd = s.split(" \\| ",10);
////        for (String d : dd){
////            System.out.println(d);
////        }
//    }
    public static void main(String[] args) {
        String aa = "I | lekj | lwkejflk | 12354f | 35trwet | 1434fsd | 234gf34 | g34gq | 00054 | 0000000001 | g4gaseg4 | gas | g4 |  | asg | sd | g4 | g | asd | g | 4 | gas | g | 4g | asg | 4 | fdh | gj | r55 | b5 | eb | 4 | b | 43b | 4v | w4 | c | srg |  | 4 |  | v4 | v | 4 | v4f | e | e45g | 4 | g34 | gq | g | 34 | ge | 4b | 34 | t | aw | f | 4waf | aw | 3f | wg | 4wg | w |  | gh | r | jrh5e | y | 4 |  | jne | aaa | s | f | \n";
        String bb = "I | lekj | lwkejflk | 12354f | 35trwet | 1434fsd | 234gf34 | g34gq | 00054 | 0000000002 | g4gaseg4 | gas | g4 |  | asg | sd | g4 | g | asd | g | 4 | gas | g | 4g | asg | 4 | fdh | gj | r55 | b5 | eb | 4 | b | 43b | 4v | w4 | c | srg |  | 4 |  | v4 | v | 4 | v4f | e | e45g | 4 | g34 | gq | g | 34 | ge | 4b | 34 | t | aw | f | 4waf | aw | 3f | wg | 4wg | w |  | gh | r | jrh5e | y | 4 |  | jne | aaa | s | f | \n";
        String cc = "I | lekj | lwkejflk | 12354f | 35trwet | 1434fsd | 234gf34 | g34gq | dsfgsfdg5 | sdfgrwg4 | g4gaseg4 | gas | g4 |  | asg | sd | g4 | g | asd | g | 4 | gas | g | 4g | asg | 4 | fdh | gj | r55 | b5 | eb | 4 | b | 43b | 4v | w4 | c | srg |  | 4 |  | v4 | v | 4 | v4f | e | e45g | 4 | g34 | gq | g | 34 | ge | 4b | 34 | t | aw | f | 4waf | aw | 3f | wg | 4wg | w |  | gh | r | jrh5e | y | 4 |  | jne | aaa | s | f | \n";
        StringBuffer sb = new StringBuffer();
        sb.append(aa);
        sb.append(bb);
        for (int i = 0;i<98;i++){
            sb.append(cc);
        }
        for (int i = 0;i<10000;i++) {
            System.out.println(i);
            fileChaseFW("C:\\Users\\xsl\\Desktop\\qwer.txt", sb.toString());
        }
    }
    static void readAppointedLineNumber(File sourceFile, int lineNumber) throws IOException {
        FileReader in = new FileReader(sourceFile);
        LineNumberReader reader = new LineNumberReader(in);
        String s = null;
        int line = 1;
        int total = getTotalLines(sourceFile);
        if (lineNumber < 0 || lineNumber > total) {
            System.out.println("不在文件的行数范围之内。");
        } else {

            System.out.println("当前行号为:" + reader.getLineNumber());

            reader.setLineNumber(23);
            System.out.println("更改后行号为:" + reader.getLineNumber());
            long i = reader.getLineNumber();
            while (reader.readLine() != null) {
                line++;
                if (i == line) {
                    s = reader.readLine();
                    System.out.println(s);
                    break;
                }

            }

        }

        reader.close();
        in.close();

    }

    // 文件内容的总行数。
    static int getTotalLines(File file) throws IOException {
        FileReader in = new FileReader(file);
        LineNumberReader reader = new LineNumberReader(in);
        String s = reader.readLine();
        int lines = 0;
        while (s != null) {
            lines++;
            s = reader.readLine();
        }
        reader.close();
        in.close();
        return lines;
    }



    /**
     * 给出类型"F"or"D"和路径,创建文件或文件夹
     *
     * @param type "F"=文件,"D"=文件夹
     * @param Path 路径 D:/xxx/xxx/xx.xxx or D:/xxx/xx
     * @return boolean
     */
    public static boolean createFileOrDir(String type, String Path) {
        if (type != null && !type.equals("")) {
            if (type.equalsIgnoreCase("F")) {
                File file = new File(Path).getParentFile();
                if (!file.exists()) {
                    file.mkdirs();
                }
                try {
                    file = new File(Path);
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            } else if (type.equalsIgnoreCase("D")) {
                File file = new File(Path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 返回"/yyyy.mm.dd上线"字符串
     *
     * @return
     */
    private static String getYMD() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String result = dateFormat.format(now);
        result = "/" + result + "上线";
        return result;
    }

    /**
     * 传入目标文件路径,读出每一行内容
     *
     * @param filePath
     * @return List<String>
     */
    public static List<String> readTxtLines(String filePath) {
        List<String> strs = new ArrayList<String>();
        File file = new File(filePath);
        InputStreamReader read = null;
        BufferedReader br = null;
        try {
            read = new InputStreamReader(new FileInputStream(
                    file), "utf-8");
            br = new BufferedReader(read);// 构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                strs.add(s);
            }
            br.close();
            return strs;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 调用系统copy方法复制文件
     * 若存在,会覆盖原有文件
     * D:/xxx/xxxx/xxx/xx.txt
     *
     * @param fromPath 源文件路径
     * @param toPath   目标路径
     * @return
     */
    public static boolean copy(String fromPath, String toPath) {
        fromPath = fromPath.replaceAll("/", "\\\\");
        toPath = toPath.replaceAll("/", "\\\\");
        try {
            Runtime.getRuntime().exec("cmd.exe /c copy " + fromPath + " " + toPath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 把指定的内容写入到指定文件
     *
     * @param fileName   文件名
     * @param parentPath 文件所在目录
     * @param content    内容<String>
     * @return
     */
    public static boolean writeToTxt(String fileName, String parentPath, String content) {
        File fileDir = new File(parentPath);
        BufferedWriter bw = null;
        try {
            fileDir.mkdirs();
            File fileTxt = new File(fileDir.getPath() + "/" + fileName);
            bw = new BufferedWriter(new FileWriter(fileTxt));
            bw.write(content);
            bw.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 创建文件
     * @param fileName
     * @return
     */
    public static boolean createFile(File fileName)throws Exception{
        try{
            if(!fileName.exists()){
                fileName.createNewFile();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }


    /**
     *读取TXT内容
     * @param file
     * @return
     */
    public static String readTxtFile(File file){
        String result = "";
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file),"gbk");
            BufferedReader br = new BufferedReader(reader);
            String s = null;
            while((s=br.readLine())!=null){
                result = result  + s;
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 写入TXT，覆盖原内容
     * @param content
     * @param fileName
     * @return
     * @throws Exception
     */
    public static boolean writeTxtFile(String content,File fileName)throws Exception{
        RandomAccessFile mm=null;
        boolean flag=false;
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(content.getBytes("gbk"));
            fileOutputStream.close();
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 写入TXT，追加写入
     * @param filePath
     * @param content
     */
    public static void fileChaseFW(String filePath, String content) {
        try {
            //构造函数中的第二个参数true表示以追加形式写文件
            FileWriter fw = new FileWriter(filePath,true);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            System.out.println("文件写入失败！" + e);
        }
    }

    private static String getStr(Object str, String edefaultStrfaultStr) {
        if (str == null || str.toString().trim().equals("")) {
            return edefaultStrfaultStr;
        }
        return str.toString().trim();
    }
}
