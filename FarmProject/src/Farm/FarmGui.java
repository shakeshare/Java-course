package Farm;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class FarmGui {

    public static void main(String[] args) {
        Farm myFarm = new Farm();
        FarmFrame window = new FarmFrame(myFarm);
        window.setTitle("可视化农场管理系统");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true); // 显示窗口

    }
}

class FarmFrame extends JFrame {
    private Farm myFarm;
    private JTextArea textArea;
    private JPanel centerPanel;
    private JTextField rowField2 = new JTextField(5);
    private JTextField posField = new JTextField(5);
    private int selectedRow = -1;
    private int selectedCol = -1;
    private JButton lastSelectedBtn = null;
    private String saveFile = "farm.txt";

    public FarmFrame(Farm farm) {
        this.myFarm = farm;
        File file = new File(saveFile);
        if (file.exists()) {
            myFarm.Load(saveFile); // 程序启动时，可以先判断本地是否存在保文件
        }
        setLayout(new BorderLayout());
        this.textArea = new JTextArea(20, 40);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setBorder(BorderFactory.createTitledBorder("执行结果"));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        add(textArea, "East");// 将文本区域添加到窗口的东侧

        JPanel leftpan = new JPanel();
        leftpan.setLayout(new GridLayout(0, 1));
        add(leftpan, "West");// 将面板添加到窗口的西侧

        JPanel initPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        initPanel.add(new JLabel("农场行数:"));
        JTextField rowsField = new JTextField(5);
        initPanel.add(rowsField);
        JButton initBtn = new JButton("初始化");
        initPanel.add(initBtn);
        initPanel.setBorder(BorderFactory.createTitledBorder("初始化农场"));
        leftpan.add(initPanel); // 将初始化面板添加到左侧面板

        JPanel addPanel = new JPanel(new GridLayout(0, 2));
        addPanel.setBorder(BorderFactory.createTitledBorder("添加农场对象"));
        addPanel.add(new JLabel("对象类型:"));
        String[] types = { "Wheat", "Corn", "Chicken", "Cow" };
        JComboBox<String> typeCombo = new JComboBox<>(types);
        addPanel.add(typeCombo);
        addPanel.add(new JLabel("对象编号:"));
        JTextField idField = new JTextField(10);
        addPanel.add(idField);
        addPanel.add(new JLabel("对象名称:"));
        JTextField nameField = new JTextField(10);
        addPanel.add(nameField);
        addPanel.add(new JLabel("添加到行:"));
        JTextField rowField = new JTextField(5);
        addPanel.add(rowField);
        addPanel.add(new JLabel("")); // 占位
        JButton addBtn = new JButton("添加");
        addPanel.add(addBtn);
        leftpan.add(addPanel); // 将添加面板添加到左侧面板

        JPanel searchPanel = new JPanel(new GridLayout(0, 2));
        searchPanel.setBorder(BorderFactory.createTitledBorder("按名称查询"));
        searchPanel.add(new JLabel("对象名称:"));
        JTextField searchNameField = new JTextField(10);
        searchPanel.add(searchNameField);
        searchPanel.add(new JLabel(""));
        JButton searchBtn = new JButton("查询");
        searchPanel.add(searchBtn);
        leftpan.add(searchPanel); // 将查询面板添加到左侧面板

        JPanel OperatePanel = new JPanel(new GridLayout(0, 2));
        OperatePanel.setBorder(BorderFactory.createTitledBorder("按位置操作"));
        OperatePanel.add(new JLabel("行号:"));
        rowField2 = new JTextField(5);
        OperatePanel.add(rowField2);
        OperatePanel.add(new JLabel("位置编号:"));
        posField = new JTextField(5);
        OperatePanel.add(posField);
        JButton careBtn = new JButton("照料对象");
        OperatePanel.add(careBtn);
        JButton RemoveBtn = new JButton("收获/出售");
        OperatePanel.add(RemoveBtn);
        leftpan.add(OperatePanel); // 将操作面板添加到左侧面板

        JPanel OtherPanel = new JPanel(new GridLayout(0, 2));
        OtherPanel.setBorder(BorderFactory.createTitledBorder("其他功能"));
        JButton PrintBtn = new JButton("输出所有");
        OtherPanel.add(PrintBtn);
        JButton ClearBtn = new JButton("清空农场");
        OtherPanel.add(ClearBtn);
        JButton SaveBtn = new JButton("保存文件");
        OtherPanel.add(SaveBtn);
        JButton LoadBtn = new JButton("加载文件");
        OtherPanel.add(LoadBtn);
        JButton RefreshBtn = new JButton("刷新界面");
        OtherPanel.add(RefreshBtn);
        JButton ExitBtn = new JButton("保存退出");
        OtherPanel.add(ExitBtn);
        leftpan.add(OtherPanel); // 将其他功能面板添加到左侧面板

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        JScrollPane centerScroll = new JScrollPane(centerPanel);
        add(centerScroll, BorderLayout.CENTER);
        refreshCenterPanel(); // 初始显示

        initBtn.addActionListener(e -> {
            String rowsText = rowsField.getText();
            if (!rowsText.isEmpty()) {
                int rows = Integer.parseInt(rowsText);
                if (rows > 0 && myFarm.initFarm(rows)) {
                    textArea.append("农场已初始化，行数（0起始）: " + rows + "\n");
                    refreshCenterPanel();
                } else {
                    textArea.append("请输入有效的行数\n");
                }
            }
        });

        addBtn.addActionListener(e -> {
            if (!myFarm.isInitialized()) {
                textArea.append("请先初始化农场\n");
                return;
            }
            String type = (String) typeCombo.getSelectedItem();
            String idText = idField.getText();
            String name = nameField.getText();
            String rowText = rowField.getText();
            if (!idText.isEmpty() && !name.isEmpty() && !rowText.isEmpty()) {
                int idx = Integer.parseInt(idText);
                int row = Integer.parseInt(rowText);
                FarmObject obj = null;
                switch (type) {
                    case "Wheat":
                        obj = new Wheat(idx, name);
                        break;
                    case "Corn":
                        obj = new Corn(idx, name);
                        break;
                    case "Chicken":
                        obj = new Chicken(idx, name);
                        break;
                    case "Cow":
                        obj = new Cow(idx, name);
                        break;
                }

                if (obj != null && myFarm.AddFarmObject(obj, row) == true) {

                    textArea.append("已添加对象: " + type + ", 编号: " + idx + ", 名称: " + name + ", 行: " + row + "\n");
                    refreshCenterPanel();
                } else {
                    textArea.append("请输入正确的对象信息\n");
                }
            }
        });

        searchBtn.addActionListener(e -> {
            String name = searchNameField.getText();
            if (!name.isEmpty()) {
                FarmObject obj = myFarm.SearchFarmObject(name);
                if (obj != null) {
                    textArea.append("找到对象: " + obj + "\n");
                } else {
                    textArea.append("未找到对象，名称: " + name + "\n");
                }
            } else {
                textArea.append("请输入对象名称进行查询\n");
            }
        });

        careBtn.addActionListener(e -> {
            String rowText = rowField2.getText();
            String posText = posField.getText();
            if (rowText.isEmpty() || posText.isEmpty()) {
                textArea.append("请输入行号和位置编号进行照料\n");
                return;
            }
            int row = Integer.parseInt(rowText);
            int pos = Integer.parseInt(posText);
            if (myFarm.getObject(row, pos) != null && row >= 0 && row < myFarm.getsize()) {
                myFarm.care(row, pos);
                textArea.append("已照料对象，行: " + row + ", 位置: " + pos + "\n");
            } else {
                textArea.append("无效位置\n");
            }
        });

        RemoveBtn.addActionListener(e -> {
            String rowText = rowField2.getText();
            String posText = posField.getText();
            if (rowText.isEmpty() || posText.isEmpty()) {
                textArea.append("请输入行号和位置编号进行收获/出售\n");
                return;
            }
            int row = Integer.parseInt(rowText);
            int pos = Integer.parseInt(posText);
            if (myFarm.getObject(row, pos) != null && row >= 0 && row < myFarm.getsize()) {
                myFarm.RemoveObject(row, pos);
                textArea.append("已收获/出售对象，行: " + row + ", 位置: " + pos + "\n");
                refreshCenterPanel();
            } else {
                textArea.append("无效位置\n ");
            }
        });

        PrintBtn.addActionListener(e -> {
            textArea.append("当前农场对象列表：\n");
            for (int i = 0; i < myFarm.getsize(); i++) {
                for (FarmObject obj : myFarm.getRow(i)) {
                    textArea.append("行: " + i + ", " + obj + "\n");
                }
            }
        });

        ClearBtn.addActionListener(e -> {
            myFarm.clearAll();
            textArea.append("农场已清空\n");
            refreshCenterPanel();
        });

        SaveBtn.addActionListener(e -> {
            if (!myFarm.isInitialized()) {
                textArea.append("请先初始化农场\n");
                return;
            }
            if (myFarm.Save(saveFile)) {
                textArea.append("农场已保存到文件\n");
            } else {
                textArea.append("保存失败\n");
            }
        });

        LoadBtn.addActionListener(e -> {
            if (myFarm.Load(saveFile)) {
                textArea.append("农场已从文件加载\n");
                refreshCenterPanel();
            } else {
                textArea.append("加载失败，文件不存在或格式错误\n");
            }
        });

        RefreshBtn.addActionListener(e -> {
            textArea.setText("界面已刷新\n");
            refreshCenterPanel();
        });

        ExitBtn.addActionListener(e -> {
            if (myFarm.isInitialized()) {
                boolean success = myFarm.Save(saveFile);
                if (success) {
                    textArea.append("农场已保存到文件，程序退出\n");
                } else {
                    textArea.append("保存失败，程序退出\n");
                }
            } else {
                textArea.append("农场未初始化，直接退出\n");
            }
            System.exit(0);
        });
    }

    private void refreshCenterPanel() {
        centerPanel.removeAll();

        if (!myFarm.isInitialized()) {
            centerPanel.add(new JLabel("农场未初始化，请先初始化"));
        } else {
            ArrayList<ArrayList<FarmObject>> allRows = myFarm.getAllRows();
            for (int i = 0; i < allRows.size(); i++) {
                ArrayList<FarmObject> rowList = allRows.get(i);
                JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                rowPanel.setBorder(BorderFactory.createTitledBorder("第 " + i + " 行"));

                for (int j = 0; j < rowList.size(); j++) {
                    FarmObject obj = rowList.get(j);
                    JButton btn = new JButton();
                    if (obj.type.equals("Cow")) {
                        btn.setText("🐂" + obj.name);
                    } else if (obj.type.equals("Chicken")) {
                        btn.setText("🐔" + obj.name);
                    } else if (obj.type.equals("Wheat")) {
                        btn.setText("🌾" + obj.name);
                    } else if (obj.type.equals("Corn")) {
                        btn.setText("🌽" + obj.name);
                    }
                    btn.setPreferredSize(new Dimension(120, 45)); 
                    btn.putClientProperty("row", i); 
                    btn.putClientProperty("col", j); 

                    btn.addActionListener(e -> {
                        
                        if (lastSelectedBtn != null) {
                            lastSelectedBtn.setBackground(null);
                        }
                        btn.setBackground(Color.LIGHT_GRAY);
                        lastSelectedBtn = btn;  // 更新最后选中的按钮

                       
                        selectedRow = (int) btn.getClientProperty("row");
                        selectedCol = (int) btn.getClientProperty("col");
                           // 记录选中行列
                       
                        rowField2.setText(String.valueOf(selectedRow));
                        posField.setText(String.valueOf(selectedCol));
                    });

                    rowPanel.add(btn);
                }
                centerPanel.add(rowPanel);
            }
        }

        centerPanel.revalidate();
        centerPanel.repaint();
    }
}
