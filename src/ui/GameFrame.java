package ui;

import domain.User;
import domain.monster.jennyTurtle;
import domain.monster.mythicalFrogWeed;
import domain.pokeMon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameFrame extends JFrame implements KeyListener, ActionListener {

    JMenuItem monster=new JMenuItem("精灵");
    JMenuItem bag=new JMenuItem("背包");
    JMenuItem exit=new JMenuItem("退出");
    JMenuItem pic=new JMenuItem("图片");

    Map<String,Integer> map=new HashMap<String,Integer>(){{
        put("fire",0);
        put("water",1);
        put("grass",2);
    }};
    double[][] restraintRelationship={{1,0.5,2},{2,1,0.5},{0.5,2,1}};
    String status="main";
    int q1,q2,q3,v1,v2,v3;
    User user=new User(7,0);
    pokeMon oppMon=new pokeMon();
    boolean catchFlag=false;
    int[][] data={{1,1,1,1,2,2,2,2},
        {1,1,1,1,2,2,2,2},{1,1,1,1,0,0,0,0},{2,2,1,1,1,1,1,1},{2,2,1,1,1,1,1,1},{0,0,1,1,1,1,0,0},
        {1,1,1,1,1,1,1,1},{1,1,1,1,1,0,0,0}};


    public GameFrame() throws HeadlessException {
        init();
        initImage();
        this.setVisible(true);
    }

    //取随机草丛
    public void shuffle(){
        Random random=new Random();
        q1=3+random.nextInt(2);
        v1=random.nextInt(2);
        q2=random.nextInt(2);
        v2=4+random.nextInt(4);
        q3=random.nextInt(2);
        v3=4+random.nextInt(4);
    }

    //随机出现精灵
    public void shufflePokemon(){
        Random random=new Random();
        int key=random.nextInt(2);
        oppMon=key==1?new jennyTurtle():new mythicalFrogWeed();
    }

    //清空图层
    public void clear(){
        this.getContentPane().removeAll();
        this.getContentPane().repaint();
    }

    //初始化菜单
    public JMenuBar initMenu(){
        JMenuBar jMenuBar=new JMenuBar();
        JMenu setMenu=new JMenu("用户");
        JMenu about=new JMenu("关于");
        setMenu.add(monster);
        monster.addActionListener(this);
        setMenu.add(bag);
        setMenu.add(exit);
        exit.addActionListener(this);
        about.add(pic);
        jMenuBar.add(setMenu);
        jMenuBar.add(about);
        return jMenuBar;
    }

    //初始化图片
    public void initImage(){
        status="main";
        shuffle();
        shufflePokemon();
        clear();
        for(int i=0;i<8;i++){
            for (int j = 0; j < 8; j++) {
                JLabel label;
                if(i== user.getI()&&j== user.getJ()&&data[user.getI()][user.getJ()]==1){
                    label = new JLabel(new ImageIcon("D:\\game_01\\img\\user.png"));
                }
                else if(i==user.getI()&&j== user.getJ()&&data[user.getI()][user.getJ()]==2){
                    label = new JLabel(new ImageIcon("D:\\game_01\\img\\uin2.png"));
                }
                else{
                    label = new JLabel(new ImageIcon("D:\\game_01\\img\\" + Integer.toString(data[i][j]) + ".png"));
                }
                label.setBounds(j*80,i*80,80,80);
                this.getContentPane().add(label);
            }
        }
        if((user.getI()==q1&&user.getJ()==v1)||(user.getI()==q2&&user.getJ()==v2)||(user.getI()==q3&&user.getJ()==v3)){
            initAttack();
        }

    }

    public void initAttack(){
        if(user.getPokeMons().get(0).getHp()>0&&oppMon.getHp()>0&&!catchFlag){
            battleInfo();
        }
        else if(oppMon.getHp()<=0){
            int myLv=user.getPokeMons().get(0).getLv();
            int oppLv=oppMon.getLv();
            double oriExp=user.getPokeMons().get(0).getMaxExp();
            user.getPokeMons().get(0).setExp(user.getPokeMons().get(0).getExp()+myLv==oppLv?10.0:10.0/(myLv-oppLv));
            if(user.getPokeMons().get(0).getExp()>oriExp){
                while(user.getPokeMons().get(0).getExp()>user.getPokeMons().get(0).getMaxExp()){
                    user.getPokeMons().get(0).setLv(user.getPokeMons().get(0).getLv()+1);
                    user.getPokeMons().get(0).setExp(user.getPokeMons().get(0).getExp()%user.getPokeMons().get(0).getMaxExp());
                    user.getPokeMons().get(0).setMaxExp(user.getPokeMons().get(0).getMaxExp()+10);
                    user.getPokeMons().get(0).setMaxHp(user.getPokeMons().get(0).getMaxHp()+5);
                }
                initImage();
            }
        }
        else{
            initImage();
        }
    }

    public void battleInfo(){
        clear();
        status="battle";
        //加载我方和对方精灵信息
        JLabel myMonster=new JLabel(new ImageIcon("D:\\game_01\\monster\\"+user.getPokeMons().get(0).getName()+".jpg"));
        myMonster.setBounds(0,400,100,100);
        this.getContentPane().add(myMonster);
        JLabel myNameAndHp=new JLabel("<html>Lv:"+user.getPokeMons().get(0).getLv()+" "+user.getPokeMons().get(0).getName()
                + "<br/><br/>HP:"+user.getPokeMons().get(0).getHp()+"/"+user.getPokeMons().get(0).getMaxHp()+"</html>");
        myNameAndHp.setBounds(100,400,200,50);
        this.getContentPane().add(myNameAndHp);
        JLabel oppMonster=new JLabel(new ImageIcon("D:\\game_01\\monster\\"+oppMon.getName()+".jpg"));
        oppMonster.setBounds(300,0,100,100);
        this.getContentPane().add(oppMonster);
        JLabel oppNameAndHp=new JLabel("<html>Lv:"+oppMon.getLv()+" "+oppMon.getName()
                +"<br/><br/>HP:"+oppMon.getHp()+"/"+oppMon.getMaxHp()+"</html>");
        oppNameAndHp.setBounds(400,0,200,50);
        this.getContentPane().add(oppNameAndHp);

        //加载我方技能按钮图标
        JButton jButton1=new JButton(user.getPokeMons().get(0).getSkills().get(0).getSkillName());
        JButton jButton2=new JButton(user.getPokeMons().get(0).getSkills().get(1).getSkillName());
        JButton jButton3=new JButton(user.getPokeMons().get(0).getSkills().get(2).getSkillName());
        JButton jButton4=new JButton(user.getPokeMons().get(0).getSkills().get(3).getSkillName());
        JButton jButton5=new JButton("catch");
        jButton1.setBounds(0,500,100,50);
        jButton2.setBounds(100,500,100,50);
        jButton3.setBounds(0,550,100,50);
        jButton4.setBounds(100,550,100,50);
        if(user.getPokeMons().get(0).getSkills().get(3).getSkillType()=="-")jButton4.setEnabled(false);
        jButton5.setBounds(200,550,100,50);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                damageAttack(0);
            }
        });
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                damageAttack(1);
            }
        });
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                damageAttack(2);
            }
        });
        jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                damageAttack(3);
            }
        });
        jButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random=new Random();
                String myType=user.getPokeMons().get(0).getType();
                String oppType=oppMon.getType();
                int num=random.nextInt(2);
                if(num==1){
                    user.getPokeMons().add(oppMon);
                    initImage();
                }
                else{
                    int s=random.nextInt(3);
                    String oppSkill=oppMon.getSkills().get(s).getSkillType();
                    user.getPokeMons().get(0).setHp(user.getPokeMons().get(0).getHp()-(int)(restraintRelationship[map.get(oppSkill)][map.get(myType)]*
                            oppMon.getSkills().get(s).getSkillAtk()));
                    initAttack();
                }
            }
        });
        this.getContentPane().add(jButton1);
        this.getContentPane().add(jButton2);
        this.getContentPane().add(jButton3);
        this.getContentPane().add(jButton4);
        this.getContentPane().add(jButton5);
    }

    //选择伤害技能
    public void damageAttack(int index){
        int attack=user.getPokeMons().get(0).getSkills().get(index).getSkillAtk();
        String mySkill=user.getPokeMons().get(0).getSkills().get(index).getSkillType();
        String myType=user.getPokeMons().get(0).getType();
        String oppType=oppMon.getType();
        oppMon.setHp(oppMon.getHp()-(int)(restraintRelationship[map.get(mySkill)][map.get(oppType)]*attack));
        Random random=new Random();
        int s=random.nextInt(3);
        String oppSkill=oppMon.getSkills().get(s).getSkillType();
        user.getPokeMons().get(0).setHp(user.getPokeMons().get(0).getHp()-(int)(restraintRelationship[map.get(oppSkill)][map.get(myType)]*
                oppMon.getSkills().get(s).getSkillAtk()));
        initAttack();
    }

    //初始化精灵显示
    public void monsterShow(){
        clear();
        ArrayList<pokeMon> pokeMons = user.getPokeMons();
        System.out.println(pokeMons);
        for (int i = 0; i < pokeMons.size(); i++) {
            JLabel img=new JLabel(new ImageIcon("D:\\game_01\\monster\\"+ pokeMons.get(i).getName()+".jpg"));
            img.setBounds(0,i*100, 100, 100);
            JLabel info=new JLabel("<html>LV: "+pokeMons.get(i).getLv()+"  "+"<br/><br/>HP: "+pokeMons.get(i).getHp()
                    +"/"+pokeMons.get(i).getMaxHp()+"</html>");
            info.setBounds(100, i*100, 100, 100);
            this.getContentPane().add(img);
            this.getContentPane().add(info);
        }
        JButton exit=new JButton("退出");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initImage();
            }
        });
        exit.setBounds(440,540, 200, 100);
        this.getContentPane().add(exit);

    }

    //初始化界面
    public void init(){
        this.setSize(640,710);
        this.setTitle("主界面");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //关闭游戏
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置菜单
        this.setJMenuBar(initMenu());
        //取消默认居中放置
        this.setLayout(null);
        //设置监听事件
        this.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if(obj==exit){
            //退出
            System.exit(0);
        }
        else if(obj==monster){
            //显示精灵
            monsterShow();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==38){
            //上
            if(user.getI()-1>=0&&data[user.getI()-1][user.getJ()]!=0&&status=="main"){
                user.setI(user.getI()-1);
                initImage();
            }
        }
        else if(code==40){
            //下
            if(user.getI()+1<=7&&data[user.getI()+1][user.getJ()]!=0&&status=="main"){
                user.setI(user.getI()+1);
                initImage();
            }
        }
        else if(code==37){
            //左
            if(user.getJ()-1>=0&&data[user.getI()][user.getJ()-1]!=0&&status=="main"){
                user.setJ(user.getJ()-1);
                initImage();
            }
        }
        else if(code==39){
            //右
            if(user.getJ()+1<=7&&data[user.getI()][user.getJ()+1]!=0&&status=="main"){
                user.setJ(user.getJ()+1);
                initImage();
            }
        }

    }
}
