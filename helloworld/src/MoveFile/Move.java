package MoveFile;

import java.io.*;
import java.util.*;

public class Move {
    public int count = 0;
    public ArrayList<File> list = new ArrayList<>();
    public File OriginDir;
    public File TargetDir;

    Move(String OriginDir, String TargetDir) {
        this.OriginDir = new File(OriginDir);
        this.TargetDir = new File(TargetDir);
    }

    public static void main(String[] args) {
        Move move = new Move("D:\\Java-course\\helloworld\\src\\MoveFile\\", "D:\\copyjava");
        try {
            move.getFile(move.OriginDir);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (File f : move.list) {
            String name = f.getName();
            if (!move.TargetDir.exists()) {
                move.TargetDir.mkdirs();
            }
            File target = new File(move.TargetDir, name);
            try {
                move.copy(f, target);

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(move.count);
        }

    }

    public void getFile(File dir) throws IOException {
        if (dir != null && dir.isDirectory()) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

        File[] files = dir.listFiles();
        for (File f : files) {
            String name = f.getName();
            if (name.contains(".java")) {
                count++;
                this.list.add(f);

            }
        }
    }

    public void copy(File source, File target) throws IOException {
        try (FileInputStream in = new FileInputStream(source);
                FileOutputStream out = new FileOutputStream(target)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }
}
