import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

//Süre sınırı
public class Main implements ActionListener {

    public static JFrame frame;
    public static JTextField cevapKutucugu;
    JPanel[] paneller = new JPanel[5];
    public static JPanel oyunPaneli, oyunBittiPaneli, anaPanel, modSecimPaneli, zorlukSecimPaneli;
    JButton[] modSecimButonlari= new JButton[5];
    JButton[] zorlukSecimButonlari = new JButton[4];
    JButton[] oyunBittiButonlari = new JButton[2];
    JButton tamamButonu, yenidenDeneButonu, baslaButonu, anaMenuButonu;
    public static JButton karisikModButonu, toplamaModuButonu, cikarmaModuButonu, carpmaModuButonu, bolmeModuButonu;
    JButton kolayModButonu, ortaModButonu, zorModButonu, ilerlemeModuButonu;
    public static JLabel soruYazisi, skorYazisi, oyunBittiYazisi, aritmetikESYazisi, versionYazisi, yapimciYazisi, oyunBittiSkorYazisi;
    public static JLabel sureYazisi;
    JLabel modSecinYazisi, zorlukSecinYazisi;
    Font font = new Font("Calibri", Font.ITALIC, 30);
    public static String soru, skor;
    public static int sayi1,sayi2, sonuc, skorSayaci = 0, zorluk = 0, modSecimi = 0;
    public static int altLimit = 1, ustLimit = 10, sure = 10;
    Timer timer;
    TimerTask timerTask;

    Main() {

        frame = new JFrame("AritmetikES v0.1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 450);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        aritmetikESYazisi = new JLabel("AritmetikES");
        aritmetikESYazisi.setFont(new Font("Broadway", Font.BOLD, 40));
        aritmetikESYazisi.setForeground(Color.darkGray);
        aritmetikESYazisi.setBounds(70, 10, 500, 100);
        aritmetikESYazisi.setVisible(true);

        versionYazisi = new JLabel("v0.1");
        versionYazisi.setFont(new Font("Calibri", Font.PLAIN, 20));
        versionYazisi.setForeground(Color.red);
        versionYazisi.setBounds(25, 365, 300, 50);
        versionYazisi.setVisible(true);

        yapimciYazisi = new JLabel("Mustafa Enes Türker");
        yapimciYazisi.setFont(new Font("Calibri", Font.ITALIC, 20));
        yapimciYazisi.setForeground(Color.red);
        yapimciYazisi.setBounds(240, 365, 300, 50);
        yapimciYazisi.setVisible(true);

        baslaButonu = new JButton("Başla");
        baslaButonu.setFont(font);
        baslaButonu.setBounds(165, 250, 110, 60);
        baslaButonu.setFocusable(false);
        baslaButonu.setForeground(Color.black);
        baslaButonu.setBackground(Color.lightGray);
        baslaButonu.addActionListener(this);
        baslaButonu.setVisible(true);

        anaPanel = new JPanel();
        anaPanel.add(aritmetikESYazisi);
        anaPanel.add(yapimciYazisi);
        anaPanel.add(versionYazisi);
        anaPanel.add(baslaButonu);
        anaPanel.setVisible(true);

        modSecinYazisi = new JLabel("Mod seçiniz.");
        modSecinYazisi.setFont(font);
        modSecinYazisi.setBackground(Color.black);
        modSecinYazisi.setBounds(130, 50, 250, 30);
        modSecinYazisi.setVisible(true);

        karisikModButonu = new JButton("Karışık");
        karisikModButonu.setFont(new Font("Cabrial", Font.BOLD, 18));
        karisikModButonu.setBounds(175, 180, 100, 60);
        karisikModButonu.setBackground(Color.blue);
        karisikModButonu.setForeground(Color.white);

        toplamaModuButonu = new JButton("+");
        toplamaModuButonu.setFont(new Font("Cabrial", Font.BOLD, 30));
        toplamaModuButonu.setBounds(25, 250, 90, 60);

        cikarmaModuButonu = new JButton("-");
        cikarmaModuButonu.setFont(new Font("Cabrial", Font.BOLD, 30));
        cikarmaModuButonu.setBounds(125, 250, 90, 60);

        carpmaModuButonu = new JButton("*");
        carpmaModuButonu.setFont(new Font("Cabrial", Font.BOLD, 30));
        carpmaModuButonu.setBounds(225, 250, 90, 60);

        bolmeModuButonu = new JButton("/");
        bolmeModuButonu.setFont(new Font("Cabrial", Font.BOLD, 30));
        bolmeModuButonu.setBounds(325, 250, 90, 60);

        modSecimButonlari[0] = karisikModButonu;
        modSecimButonlari[1] = toplamaModuButonu;
        modSecimButonlari[2] = cikarmaModuButonu;
        modSecimButonlari[3] = carpmaModuButonu;
        modSecimButonlari[4] = bolmeModuButonu;

        for (int i = 0; i < 5; i++) {
            if (modSecimButonlari[i] != karisikModButonu) {
                modSecimButonlari[i].setBackground(Color.orange);
            }
            modSecimButonlari[i].addActionListener(this);
            modSecimButonlari[i].setFocusable(false);
            modSecimButonlari[i].setVisible(true);
        }


        modSecimPaneli = new JPanel();
        modSecimPaneli.add(modSecinYazisi);
        modSecimPaneli.add(karisikModButonu);
        modSecimPaneli.add(toplamaModuButonu);
        modSecimPaneli.add(cikarmaModuButonu);
        modSecimPaneli.add(carpmaModuButonu);
        modSecimPaneli.add(bolmeModuButonu);
        modSecimPaneli.setVisible(false);

        zorlukSecinYazisi = new JLabel("Zorluk seçiniz.");
        zorlukSecinYazisi.setFont(font);
        zorlukSecinYazisi.setBackground(Color.black);
        zorlukSecinYazisi.setBounds(130, 50, 250, 30);
        zorlukSecinYazisi.setVisible(true);

        kolayModButonu = new JButton("Kolay");
        kolayModButonu.setBounds(50, 180, 100, 60);
        kolayModButonu.setBackground(Color.green);

        ortaModButonu = new JButton("Orta");
        ortaModButonu.setBounds(165, 180, 100, 60);
        ortaModButonu.setBackground(Color.yellow);

        zorModButonu = new JButton("Zor");
        zorModButonu.setBounds(280, 180, 100, 60);
        zorModButonu.setBackground(Color.red);

        ilerlemeModuButonu = new JButton("İlerleme");
        ilerlemeModuButonu.setForeground(Color.white);
        ilerlemeModuButonu.setBounds(160, 260, 110, 60);
        ilerlemeModuButonu.setBackground(Color.black);

        zorlukSecimButonlari[0] = kolayModButonu;
        zorlukSecimButonlari[1] = ortaModButonu;
        zorlukSecimButonlari[2] = zorModButonu;
        zorlukSecimButonlari[3] = ilerlemeModuButonu;

        for (int i = 0; i < 4; i++) {
            zorlukSecimButonlari[i].setFont(new Font("Cabrial", Font.BOLD, 20));
            zorlukSecimButonlari[i].addActionListener(this);
            zorlukSecimButonlari[i].setFocusable(false);
            zorlukSecimButonlari[i].setVisible(true);
        }


        zorlukSecimPaneli = new JPanel();
        zorlukSecimPaneli.add(zorlukSecinYazisi);
        zorlukSecimPaneli.add(kolayModButonu);
        zorlukSecimPaneli.add(ortaModButonu);
        zorlukSecimPaneli.add(zorModButonu);
        zorlukSecimPaneli.add(ilerlemeModuButonu);
        zorlukSecimPaneli.setVisible(false);


        soruYazisi = new JLabel();
        soruYazisi.setBounds(140, 75, 400, 50);
        soruYazisi.setForeground(Color.black);
        soruYazisi.setFont(font);
        soruYazisi.setVisible(true);

        skorYazisi = new JLabel();
        skorYazisi.setText(skor);
        skorYazisi.setBounds(15, 10, 400, 50);
        skorYazisi.setForeground(Color.darkGray);
        skorYazisi.setFont(font);
        skorYazisi.setVisible(true);

        tamamButonu = new JButton("Tamam");
        tamamButonu.setBounds(135, 265, 150, 50);
        tamamButonu.setFont(font);
        tamamButonu.setFocusable(false);
        tamamButonu.setBackground(Color.lightGray);
        tamamButonu.addActionListener(this);
        tamamButonu.setVisible(true);

        cevapKutucugu = new JTextField();
        cevapKutucugu.setFont(font);
        cevapKutucugu.setBackground(Color.white);
        cevapKutucugu.addKeyListener(new TusOkuyucu());
        cevapKutucugu.setBounds(120, 165, 180, 50);

        sureYazisi = new JLabel();
        sureYazisi.setFont(font);
        sureYazisi.setForeground(Color.blue);
        sureYazisi.setBounds(280, 10, 120, 50);
        sureYazisi.setVisible(true);

        oyunPaneli = new JPanel();
        oyunPaneli.add(tamamButonu);
        oyunPaneli.add(cevapKutucugu);
        oyunPaneli.add(sureYazisi);
        oyunPaneli.setVisible(false);

        oyunBittiYazisi = new JLabel("Oyun Bitti!");
        oyunBittiYazisi.setForeground(Color.red);
        oyunBittiYazisi.setFont(new Font("Arial", Font.BOLD, 40));
        oyunBittiYazisi.setBounds(120, 75, 400, 50);
        oyunBittiYazisi.setVisible(true);

        oyunBittiSkorYazisi = new JLabel();
        oyunBittiSkorYazisi.setBounds(140, 140, 400, 50);
        oyunBittiSkorYazisi.setForeground(Color.white);
        oyunBittiSkorYazisi.setFont(new Font("Arial", Font.BOLD, 40));

        yenidenDeneButonu = new JButton("Yeniden Dene");
        yenidenDeneButonu.setBounds(90, 250, 250, 50);

        anaMenuButonu = new JButton("Ana Menüye Dön");
        anaMenuButonu.setBounds(90, 325, 250, 50);

        oyunBittiButonlari[0] = yenidenDeneButonu;
        oyunBittiButonlari[1] = anaMenuButonu;

        for (int i = 0; i < 2; i++) {
            oyunBittiButonlari[i].setFont(font);
            oyunBittiButonlari[i].setFocusable(false);
            oyunBittiButonlari[i].setBackground(Color.lightGray);
            oyunBittiButonlari[i].addActionListener(this);
            oyunBittiButonlari[i].setVisible(true);
        }

        oyunBittiPaneli = new JPanel();
        oyunBittiPaneli.add(oyunBittiYazisi);
        oyunBittiPaneli.add(oyunBittiSkorYazisi);
        oyunBittiPaneli.add(yenidenDeneButonu);
        oyunBittiPaneli.add(anaMenuButonu);
        oyunBittiPaneli.setVisible(false);

        paneller[0] = anaPanel;
        paneller[1] = modSecimPaneli;
        paneller[2] = zorlukSecimPaneli;
        paneller[3] = oyunPaneli;
        paneller[4] = oyunBittiPaneli;

        for (int i = 0; i < 5; i++) {
            paneller[i].setBounds(0, 0, 450, 450);
            paneller[i].setBackground(Color.pink);
            paneller[i].setLayout(null);
            frame.add(paneller[i]);
        }

        frame.setVisible(true);

    }


    public static void main(String[] args) {

        new Main();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                if (sure >= 0 && oyunPaneli.isVisible()) {
                    sureYazisi.setText("Süre: " + sure + "s");
                    sure--;
                }

                if (sureYazisi.getText().equals("Süre: 0s")) {
                    oyunPaneli.setVisible(false);
                    oyunBittiSkorYazisi.setText(skor);
                    oyunBittiPaneli.setVisible(true);
                }


            }
        };


        timer.scheduleAtFixedRate(task, 0, 1000);

    }

    public static void toplamaModu() {

        switch (zorluk) {
            case 1:
                sayi1 = rastgeleSayi(1, 100);
                sayi2 = rastgeleSayi(1, 100);
                break;
            case 2:
                sayi1 = rastgeleSayi(10, 1000);
                sayi2 = rastgeleSayi(10, 1000);
                break;
            case 3:
                sayi1 = rastgeleSayi(100, 10000);
                sayi2 = rastgeleSayi(100, 10000);
                break;
            case 4:
                sayi1 = rastgeleSayi(altLimit, ustLimit);
                sayi2 = rastgeleSayi(altLimit, ustLimit);
                altLimit += 3;
                ustLimit += 10;
                break;
        }


        soru = sayi1 + " + " + sayi2 + " = ?";
        soruYazisi.setText(soru);
        oyunPaneli.add(soruYazisi);
        sonuc = sayi1 + sayi2;
        skor = "Skor: " + skorSayaci;
        skorYazisi.setText(skor);
        oyunPaneli.add(skorYazisi);

    }

    public static void cikarmaModu() {

        switch (zorluk) {
            case 1:
                sayi1 = rastgeleSayi(1, 100);
                sayi2 = rastgeleSayi(1, 100);
                break;
            case 2:
                sayi1 = rastgeleSayi(10, 1000);
                sayi2 = rastgeleSayi(10, 1000);
                break;
            case 3:
                sayi1 = rastgeleSayi(100, 10000);
                sayi2 = rastgeleSayi(100, 10000);
                break;
            case 4:
                sayi1 = rastgeleSayi(altLimit, ustLimit);
                sayi2 = rastgeleSayi(altLimit, ustLimit);
                altLimit += 3;
                ustLimit += 10;
                break;
        }

        int gecici = Math.min(sayi1, sayi2);
        sayi1 = Math.max(sayi1, sayi2);
        sayi2 = gecici;


        soru = sayi1 + " - " + sayi2 + " = ?";
        soruYazisi.setText(soru);
        oyunPaneli.add(soruYazisi);
        sonuc = sayi1 - sayi2;
        skor = "Skor: " + skorSayaci;
        skorYazisi.setText(skor);
        oyunPaneli.add(skorYazisi);

    }

    public static void carpmaModu() {

        switch (zorluk) {
            case 1:
                sayi1 = rastgeleSayi(1, 15);
                sayi2 = rastgeleSayi(1, 15);
                break;
            case 2:
                sayi1 = rastgeleSayi(5, 40);
                sayi2 = rastgeleSayi(5, 40);
                break;
            case 3:
                sayi1 = rastgeleSayi(10, 75);
                sayi2 = rastgeleSayi(10, 75);
                break;
            case 4:
                sayi1 = rastgeleSayi(altLimit, ustLimit);
                sayi2 = rastgeleSayi(altLimit, ustLimit);
                altLimit += 1;
                ustLimit += 3;
                break;
        }


        soru = sayi1 + " x " + sayi2 + " = ?";
        soruYazisi.setText(soru);
        oyunPaneli.add(soruYazisi);
        sonuc = sayi1 * sayi2;
        skor = "Skor: " + skorSayaci;
        skorYazisi.setText(skor);
        oyunPaneli.add(skorYazisi);
    }

    public static void bolmeModu() {

        int kat;

        switch (zorluk) {
            case 1:
                sayi2 = rastgeleSayi(1, 15);
                kat = rastgeleSayi(1, 10);
                sayi1 = sayi2 * kat;
                break;
            case 2:
                sayi2 = rastgeleSayi(5, 25);
                kat = rastgeleSayi(5, 25);
                sayi1 = sayi2 * kat;
                break;
            case 3:
                sayi2 = rastgeleSayi(10, 50);
                kat = rastgeleSayi(10, 50);
                sayi1 = sayi2 * kat;
                break;
            case 4:
                sayi2 = rastgeleSayi(altLimit, ustLimit);
                kat = rastgeleSayi(altLimit, ustLimit);
                sayi1 = sayi2 * kat;
                altLimit += 1;
                ustLimit += 4;
                break;
        }


        soru = sayi1 + " / " + sayi2 + " = ?";
        soruYazisi.setText(soru);
        oyunPaneli.add(soruYazisi);
        sonuc = sayi1 / sayi2;
        skor = "Skor: " + skorSayaci;
        skorYazisi.setText(skor);
        oyunPaneli.add(skorYazisi);

    }

    public static void karisikMod() {

        int rastgeleModSec = rastgeleSayi(1, 4);

        switch (rastgeleModSec) {
            case 1:
                toplamaModu();
                break;
            case 2:
                cikarmaModu();
                break;
            case 3:
                carpmaModu();
                break;
            case 4:
                bolmeModu();
                break;

        }

    }

    public static void cevabiKontrolEt() {
        if (cevapKutucugu.getText().equals(String.valueOf(sonuc))) {
            sure = 10;
            skorSayaci++;
            cevapKutucugu.setText("");
            switch (modSecimi) {
                case 1:
                    toplamaModu();
                    break;
                case 2:
                    cikarmaModu();
                    break;
                case 3:
                    carpmaModu();
                    break;
                case 4:
                    bolmeModu();
                    break;
                case 5:
                    karisikMod();
                    break;
            }

        } else {
            oyunPaneli.setVisible(false);
            oyunBittiSkorYazisi.setText(skor);
            oyunBittiPaneli.setVisible(true);
        }

    }

    public static void oyunuSifirla() {
        sure = 10;
        modSecimi = 0;
        zorluk = 0;
        skorSayaci = 0;
        altLimit = 1;
        ustLimit = 10;
        cevapKutucugu.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == baslaButonu) {
            anaPanel.setVisible(false);
            modSecimPaneli.setVisible(true);
        }

        if (e.getSource() == tamamButonu) {
            cevabiKontrolEt();
        }

        if (e.getSource() == yenidenDeneButonu) {
            sure = 10;
            skorSayaci = 0;
            cevapKutucugu.setText("");
            altLimit = 1;
            ustLimit = 10;
            switch (modSecimi) {
                case 1:
                    toplamaModu();
                    break;
                case 2:
                    cikarmaModu();
                    break;
                case 3:
                    carpmaModu();
                    break;
                case 4:
                    bolmeModu();
                    break;
                case 5:
                    karisikMod();
                    break;
            }
            oyunBittiPaneli.setVisible(false);
            oyunPaneli.setVisible(true);
        }



        for (int i = 0; i < 5; i++) {

            if (e.getSource() == modSecimButonlari[i]) {
                if (modSecimButonlari[i].equals(toplamaModuButonu)) {
                    modSecimi = 1;
                }
                if (modSecimButonlari[i].equals(cikarmaModuButonu)) {
                    modSecimi = 2;
                }
                if (modSecimButonlari[i].equals(carpmaModuButonu)) {
                    modSecimi = 3;
                }
                if (modSecimButonlari[i].equals(bolmeModuButonu)) {
                    modSecimi = 4;
                }
                if (modSecimButonlari[i].equals(karisikModButonu)) {
                    modSecimi = 5;

                }

                modSecimPaneli.setVisible(false);
                zorlukSecimPaneli.setVisible(true);

            }

        }

        for (int i = 0; i < 4; i++) {
            if (e.getSource() == zorlukSecimButonlari[i]) {
                if (zorlukSecimButonlari[i].equals(kolayModButonu)) {
                    zorluk = 1;
                }
                if (zorlukSecimButonlari[i].equals(ortaModButonu)) {
                    zorluk = 2;
                }
                if (zorlukSecimButonlari[i].equals(zorModButonu)) {
                    zorluk = 3;
                }
                if (zorlukSecimButonlari[i].equals(ilerlemeModuButonu)) {
                    zorluk = 4;
                }

                switch (modSecimi) {
                    case 1:
                        toplamaModu();
                        break;
                    case 2:
                        cikarmaModu();
                        break;
                    case 3:
                        carpmaModu();
                        break;
                    case 4:
                        bolmeModu();
                        break;
                    case 5:
                        karisikMod();
                        break;
                }

                zorlukSecimPaneli.setVisible(false);
                oyunPaneli.setVisible(true);
            }
        }


        if (e.getSource() == anaMenuButonu) {
            oyunuSifirla();
            oyunBittiPaneli.setVisible(false);
            anaPanel.setVisible(true);
        }

    }

    public class TusOkuyucu extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                cevabiKontrolEt();
            }

        }

    }

    public static int rastgeleSayi(int min, int maks) {
        Random random = new Random();
        return random.nextInt(min, (maks + 1));
    }

}






