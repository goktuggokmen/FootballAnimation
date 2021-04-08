import java.awt.ActiveEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.stream.IntStream;
import javax.swing.JButton;
import acm.graphics.GDimension;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.graphics.GResizable;
import acm.program.GraphicsProgram;
import acm.program.Program;
import acm.util.RandomGenerator;

public class FutbolAnimasyonu extends GraphicsProgram{

	int sbt = 15;
	int kaleciEn = 65;
	int kaleciBoy = 85;
	int hareket=5;
	int topEn = 100;
	int topBoy = 100;
	int cezaSahasiYarimDaireEn = 98;
	int cezaSahasiYarimDaireBoy = 37;
	int sayacGol = 0;
	int sayacGolYok = 0;

	public GImage kaleci = new GImage("C:\\Users\\GÖK2\\OneDrive - ÝZMÝR DEMOKRASÝ ÜNÝVERSÝTESÝ\\Dersler\\YAZILIM\\JAVA\\ProgramlamaProjesi\\lib\\kaleci.png");
	public GImage top = new GImage("C:\\Users\\GÖK2\\OneDrive - ÝZMÝR DEMOKRASÝ ÜNÝVERSÝTESÝ\\Dersler\\YAZILIM\\JAVA\\ProgramlamaProjesi\\lib\\top.png");
	
	public GLine direk1 = new GLine((getWidth()/2)-125,0,(getWidth()/2)+125,0);
	
	public void run() {
		addKeyListeners();

		//ARKAPLAN
		
		GRect arkaPlan = new GRect(0,0,getWidth(),getHeight());
		arkaPlan.setFilled(true);
		arkaPlan.setColor(Color.GREEN);
		add(arkaPlan);

		//ORTA SAHA ÇÝZGÝSÝ
		
		GLine ortaSahaCizgisi = new GLine(0,getHeight()-sbt,getWidth(),getHeight()-sbt);
		ortaSahaCizgisi.setColor(Color.WHITE);
		ortaSahaCizgisi.setLineWidth(5);
		add(ortaSahaCizgisi);

		//ORTA YUVARLAK
		
		GOval ortaYuvarlak = new GOval(100,100);
		ortaYuvarlak.setColor(Color.WHITE);
		ortaYuvarlak.setLineWidth(5);
		add(ortaYuvarlak,getWidth()/2-cezaSahasiYarimDaireEn/2.5,getHeight()-50);

		//KALE
		
		GLine direk1 = new GLine((getWidth()/2)-125,0,(getWidth()/2)+125,0);
		direk1.setColor(Color.WHITE);
		direk1.setLineWidth(5);
		add(direk1);
		
		GLine direk2 = new GLine((getWidth()/2)-125,0,(getWidth()/2)-125,125);
		direk2.setColor(Color.WHITE);
		direk2.setLineWidth(5);
		add(direk2);
		
		GLine direk3 = new GLine((getWidth()/2)+125,0,(getWidth()/2)+125,125);
		direk3.setColor(Color.WHITE);
		direk3.setLineWidth(5);
		add(direk3);

		//CEZA SAHASI
		
		GLine cizgi1 = new GLine((getWidth()/2)-250,(getWidth()/2)-125,(getWidth()/2)+250,(getWidth()/2)-125);
		cizgi1.setLineWidth(5);
		cizgi1.setColor(Color.WHITE);
		add(cizgi1);
		
		GLine cizgi2 = new GLine((getWidth()/2)-250,0,(getWidth()/2)-250,(getWidth()/2)-125);
		cizgi2.setColor(Color.WHITE);
		cizgi2.setLineWidth(5);
		add(cizgi2);
		
		GLine cizgi3 = new GLine((getWidth()/2)+250,0,(getWidth()/2)+250,(getWidth()/2)-125);
		cizgi3.setColor(Color.WHITE);
		cizgi3.setLineWidth(5);
		add(cizgi3);
		
		GOval penaltiNoktasi = new GOval (sbt,sbt);
		penaltiNoktasi.setColor(Color.WHITE);
		penaltiNoktasi.setFilled(true);
		penaltiNoktasi.setFillColor(Color.WHITE);
		add(penaltiNoktasi,getWidth()/2,(getWidth()/2)-175);

		//CEZA SAHASI YARIM DAÝRE
		
		GImage cezaSahaYarimDaire = new GImage("C:\\Users\\GÖK2\\OneDrive - ÝZMÝR DEMOKRASÝ ÜNÝVERSÝTESÝ\\Dersler\\YAZILIM\\JAVA\\ProgramlamaProjesi\\lib\\YarýmDaire.png");
		add (cezaSahaYarimDaire,getWidth()/2- cezaSahasiYarimDaireEn/2.5,(getWidth()/2)-123);

		//KALECÝ
		add(kaleci,(getWidth()/2)-kaleciEn/2,0+direk1.getLineWidth()+50);
		kaleci.setSize(kaleciEn, kaleciBoy);

		// TOPUN HAREKETÝ
	
		for(int topSayisi=10;topSayisi>0;topSayisi-- ) {
			int random1 = getWidth()/2-kaleciBoy-sbt;
			int random2 = getWidth()/2+kaleciBoy-sbt;
			int x = rgen.nextInt(random1,random2);
			top.setSize(2*topBoy/3, 2*topBoy/3);
			add(top,x,getHeight());
			
			if(top.getY()>=0) {	
				waitForClick();
				while(top.getY()>0 ) {
					top.move(0, -6);
					
					if(top.getX()>=kaleci.getX() && top.getY()<=(kaleci.getY()+kaleciBoy) && top.getX()<=(kaleci.getX()+kaleciEn)) {
						pause(100);
						sayacGolYok++;
						break;
					}else if (top.getX()<=kaleci.getX() && top.getY()<=(kaleci.getY()+kaleciBoy)) {
						pause(100);
						sayacGolYok++;
						break;
					}
					pause(15);
				}
			}
			
			sayacGol = (10-sayacGolYok);
		}

		if(top.getY()<=0) {
			pause(100);
			remove(top);
		}
		GLabel skor = new GLabel (" GOL SAYISI: " + sayacGol + " |" + " KALECI KURTARIÞI SAYISI: "+ sayacGolYok);
		skor.setFont("ARIAL-35");
		skor.setColor(Color.GREEN);
		
		GRect skorKutu = new GRect(skor.getWidth(),skor.getHeight());
		skorKutu.setFilled(true);
		skorKutu.setFillColor(Color.BLUE);
		add(skorKutu,10,(2*kaleciBoy)-skorKutu.getHeight()+12);
		add(skor,0,2*kaleciBoy);
		
		GLabel topBitti = new GLabel ("TOP BITTI",getWidth()/2-100,getHeight()/2+85);
		topBitti.setColor(Color.GREEN);
		topBitti.setFont("ARIAL-50");
		
		GRect yaziKutu = new GRect(topBitti.getWidth(),topBitti.getHeight());
		yaziKutu.setFilled(true);
		yaziKutu.setColor(Color.BLUE);
		yaziKutu.setFillColor(Color.BLUE);
		add(yaziKutu,getWidth()/2-100,(getHeight()/2+100)-topBitti.getHeight());
		add(topBitti);
		
		JButton cikisButon = new JButton("OYUNDAN CIKMAK ICIN BURAYA TIKLAYINIZ");
		cikisButon.setToolTipText(CENTER);
		cikisButon.setForeground(Color.WHITE);
		cikisButon.setBackground(Color.BLUE);
		cikisButon.setSize(350, 50);
		cikisButon.setFont(new Font("Arial", Font.BOLD, 14));
		add(cikisButon,getWidth()/2-160,getHeight()/2+85+topBitti.getHeight());
		addActionListeners();

	}
	public void keyPressed(KeyEvent m) {
		if (m.getKeyCode()==KeyEvent.VK_RIGHT && kaleci.getX()+kaleci.getWidth()<(getWidth()/2)+125) {
			kaleci.move(hareket, 0);
		}
		if (m.getKeyCode()==KeyEvent.VK_LEFT && kaleci.getX()>(getWidth()/2)-120) {
			kaleci.move(-hareket,0);
		}
	}
	public void actionPerformed(ActionEvent eylem) {
		String komut =eylem.getActionCommand();
		if(komut.equals("OYUNDAN CIKMAK ICIN BURAYA TIKLAYINIZ")) {
			System.exit(0);
		}
	}
	RandomGenerator rgen = RandomGenerator.getInstance();
}