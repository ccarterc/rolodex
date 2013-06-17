import java.io.*;
import java.awt.*;
import java.awt.event.*;



public class CustomerInfo extends Frame implements ActionListener, WindowListener, ItemListener     
{
  private static final int s_iNumInputs = 2;
	
	DataCard1 card1[];
	
	int m_iNumIndex[]  = new int[50];//keeps track of valid card index for 1 word search 
	int iNumIndex2[] = new int[25];//keeps track of valid card index for 2 word search
	
	int iNumCurrentValidCardIndex = 0;//number of current card of the valid finds
	int iNumFinds  = 0;//number of finds
	int iNumFinds2 = 0;//number of finds on second kwd
		
	boolean searchKwd1Only = false;
	
	private MenuBar mb;
	
	private Menu mnuFile;
	private Menu mnuEdit;
	private Menu mnuView;
	private Menu mnuSize;
			
	private MenuItem mniSave;
	private MenuItem mniClose;
	private MenuItem mniNewCard;
	private MenuItem mniClearSearch;
	private MenuItem mniNextFind;
	private MenuItem mniPreiousFind;
	
	private CheckboxMenuItem cbx600x500;
	private CheckboxMenuItem cbx600x400;
	private CheckboxMenuItem cbx500x400;
	private CheckboxMenuItem cbx500x300;
	private CheckboxMenuItem cbx400x300;
	private CheckboxMenuItem cbx400x200;
	
	private int m_iNumCards    = 0;
	private int m_iCurrentCard = 0;
	
	private Label m_lblBlank1;
	private Label m_lblBlank2;
	private Label m_lblKwd1;
	private Label m_lblKwd2;
	private Label m_lblMessage;
	private Label m_lblText;
	private Label m_lblKwd1S;
	private Label m_lblKwd2S;
	private Label m_lblCardNum;
	
	private TextArea m_txaInfo;
	
	private TextField m_txfKwd1;
	private TextField m_txfKwd2;
	private TextField m_txfText;
	private TextField m_txfKwd1S;
	private TextField m_txfKwd2S;
	
	private Button m_btnSearchKwd;
	private Button m_btnSearchText;
	private Button m_btnSaveCard;
	private Button m_btnNewCard;
	private Button m_btnNextResult;
	private Button m_btnPreviousResult;
		
	private Panel m_pnlLeft;
	private Panel m_pnlRight;
	private Panel m_pnlSearch;
	private Panel m_pnlCard;
	private Panel m_pnlButtons;
	private Panel m_pnlCenter;
	private Panel m_pnlSouth;
	private Panel m_pnlInfo;
	private Panel m_pnlKwd_Card;
		
	
	
	public CustomerInfo(String sTitle)
	{
		super(sTitle);
		
		card1 = new DataCard1[100];
		
			for(int i = 0; i < 100; i++)
			{
				card1[i] = new DataCard1();	
			}
		
				
		this.addWindowListener(this);
		
		mb = new MenuBar();
		setMenuBar(mb);
		
		mnuFile = new Menu("File", true);
		mnuEdit = new Menu("Edit", true);
		mnuView = new Menu("View", true);
		mnuSize = new Menu("Size", true);
		
		mniSave         = new MenuItem("Save");
		mniNewCard      = new MenuItem("New");
		mniClose        = new MenuItem("Close");
		mniNextFind     = new MenuItem("Next Find");
		mniPreviousFind = new MenuItem("Last Find");
		mniClearSearch  = new MenuItem("Clear Search");
		
		cbx600x500 = new CheckboxMenuItem("600 x 500");
		cbx600x400 = new CheckboxMenuItem("600 x 400");
		cbx500x400 = new CheckboxMenuItem("500 x 400");
		cbx500x300 = new CheckboxMenuItem("500 x 300");
		cbx400x300 = new CheckboxMenuItem("400 x 300");
		cbx400x200 = new CheckboxMenuItem("400 x 200");
		
		mb.add(mnuFile);
		mb.add(mnuEdit);
		mb.add(mnuView);
		
		mnuFile.add(mniSave);
		mnuFile.add(mniNewCard);
		mnuFile.addSeparator();
		mnuFile.add(mniClose);
		
		mnuEdit.add(mniNextFind);
		mnuEdit.add(mniPreviousFind);
		mnuEdit.addSeparator();
		mnuEdit.add(mniClearSearch);
		
		mnuView.add(mnuSize);
		
		mnuSize.add(cbx600x500);
		mnuSize.add(cbx600x400);
		mnuSize.add(cbx500x400);
		mnuSize.add(cbx500x300);
		mnuSize.add(cbx400x300);
		mnuSize.add(cbx400x200);
		
		mniSave.addActionListener(this);
		mniNewCard.addActionListener(this);
		mniClose.addActionListener(this);
		mniNextFind.addActionListener(this);
		mniPreviousFind.addActionListener(this);
		mniClearSearch.addActionListener(this);
		cbx600x500.addActionListener(this);
		cbx600x400.addActionListener(this);
		cbx500x400.addActionListener(this);
		cbx500x300.addActionListener(this);
		cbx400x300.addActionListener(this);
		cbx400x200.addActionListener(this);
		
		m_btnNewCard        = new Button("New");
		m_btnSaveCard       = new Button("Save");
		m_btnSearchKwd      = new Button("Search Keyword");
		m_btnSearchText     = new Button("Search Text");
		m_btnNextResult     = new Button("Next Find");
		m_btnPreviousResult = new Button("Last Find");
		
		m_btnNewCard.addActionListener(this);
		m_btnSaveCard.addActionListener(this);
		m_btnSearchKwd.addActionListener(this);
		m_btnSearchText.addActionListener(this);
		m_btnNextResult.addActionListener(this);
		m_btnPreviousResult.addActionListener(this);
		
		m_lblBlank1  = new Label();
		m_lblBlank2  = new Label();
		m_lblKwd1    = new Label("Keyword:", Label.RIGHT);
		m_lblKwd2    = new Label("Keyword:", Label.RIGHT);
		m_lblText    = new Label("Word:", Label.RIGHT); 
		m_lblMessage = new Label("Status:");
		m_lblKwd1S   = new Label("KeyWord:", Label.RIGHT);
		m_lblKwd2S   = new Label("KeyWord:", Label.RIGHT);
		m_lblCardNum = new Label("Card Number: " + 1, Label.CENTER);
		
		m_pnlRight    = new Panel();
		m_pnlLeft     = new Panel();
		m_pnlSearch   = new Panel();
		m_pnlCard     = new Panel();
		m_pnlButtons  = new Panel();
		m_pnlCenter   = new Panel();
		m_pnlSouth    = new Panel();
		m_pnlInfo     = new Panel();
		m_pnlKwd_Card = new Panel();
		
		m_txfKwd1  = new TextField();
		m_txfKwd2  = new TextField();
		m_txfText  = new TextField();
		m_txfKwd1S = new TextField();
		m_txfKwd2S = new TextField();
		
		m_txaInfo = new TextArea();
		
		m_pnlRight.setLayout(new GridLayout(1, 2));
		m_pnlLeft.setLayout(new GridLayout(2, 1));
		m_pnlSearch.setLayout(new GridLayout(4, 2));
		m_pnlCard.setLayout(new GridLayout(1, 4));
		m_pnlCenter.setLayout(new GridLayout(1, 2));
		this.setLayout(new BorderLayout());
		m_pnlButtons.setLayout(new GridLayout(4, 1));
		m_pnlSouth.setLayout(new GridLayout(1, 2));
		m_pnlInfo.setLayout(new GridLayout(2, 1));
		m_pnlKwd_Card.setLayout(new GridLayout(2, 1));
		
		m_pnlButtons.add(m_btnSearchKwd);
		m_pnlButtons.add(m_btnSearchText);
		m_pnlButtons.add(m_btnNewCard);
		m_pnlButtons.add(m_btnSaveCard);
	
		m_pnlCard.add(m_lblKwd1);
		m_pnlCard.add(m_txfKwd1);
		m_pnlCard.add(m_lblKwd2);
		m_pnlCard.add(m_txfKwd2);
		
		m_pnlKwd_Card.add(m_lblMessage);
		m_pnlKwd_Card.add(m_lblCardNum);
		
		m_pnlInfo.add(m_pnlKwd_Card);
		m_pnlInfo.add(m_pnlCard);
				
		m_pnlSouth.add(m_btnPreviousResult);
		m_pnlSouth.add(m_btnNextResult);
		
	
		m_pnlLeft.add(m_pnlInfo);
		m_pnlLeft.add(m_txaInfo);
				
		m_pnlSearch.add(m_lblText);
		m_pnlSearch.add(m_txfText);
		m_pnlSearch.add(m_lblKwd1S);
		m_pnlSearch.add(m_txfKwd1S);
		m_pnlSearch.add(m_lblKwd2S);
		m_pnlSearch.add(m_txfKwd2S);
		m_pnlSearch.add(m_lblBlank1);
		m_pnlSearch.add(m_lblBlank2);
						
		m_pnlRight.add(m_pnlButtons);
		m_pnlRight.add(m_pnlSearch);
		
		m_pnlCenter.add(m_pnlLeft);
		m_pnlCenter.add(m_pnlRight);
		
		this.add("Center", m_pnlCenter);
		this.add("South", m_pnlSouth);
		
		this.setBackground(Color.lightGray);
	}
	
	
	
	public static void main(String arg[])
	{
		CustomerInfo ci = new CustomerInfo("Customer Information");
		
		ci.setSize(800, 600);
		ci.setVisible(true);
	}
	
	
	
	private void doReadData()
	{
		FileInputStream fis1;
		DataInputStream dis;
		
		FileInputStream   fis2;
		ObjectInputStream ois;
		
		char cTemp = 'x';
		
				
		try
		{
			fis1 = new FileInputStream("objectFile2.bin");
			dis  = new DataInputStream(fis1);
			
			m_iNumCards = dis.readInt();
		}
		catch(IOException ioe)
		{
			displayMessage(ioe.toString());
			
			return;	
		}
		
		try
		{
			try
			{
				fis2 = new FileInputStream("objectFile1.bin");	
				ois = new ObjectInputStream(fis2);
								
				for(int i = 0; i < m_iNumCards; i++)
				{
					card1[i] = (DataCard1)ois.readObject();
				}
			}
			catch(ClassNotFoundException cnf)
			{
				displayMessage("" + cnf.toString());
				
				return;
			}
		}
		catch(IOException ioe)
		{
			displayMessage("" + ioe.toString());
			
			return;	
		}
	}
	
	
	
	private void doSaveToDisk()
	{
				
		DataOutputStream dos;
		ObjectOutputStream oos;
		FileOutputStream fos1;		
		FileOutputStream fos2;
		
		try
		{
			try
			{
				fos1 = new FileOutputStream("objectFile1.bin");	
				oos = new ObjectOutputStream(fos1);
								
				for(int i = 0; i < m_iNumCards; i++)
				{
					oos.writeObject(card1[i]);
				}
			}
			catch(NotSerializableException nse)
			{
				displayMessage("" + nse.toString());
				
				return;
			}
		}
		catch(IOException ioe)
		{
			displayMessage("" + ioe.toString());
			
			return;	
		}
		
		try
		{
			fos2 = new FileOutputStream("objectFile2.bin");
			dos = new DataOutputStream(fos2);
		
			dos.writeInt(m_iNumCards);
		}
		catch(IOException ioe)
		{
			displayMessage("" + ioe.toString());
			
			return;	
		}
		
		try
		{
			oos.close();
			dos.close();	
		}
		catch(IOException ioe)
		{
			displayMessage(ioe.toString());
			
			return;
		}
		
		displayMessage("Write successful!");
	}
		
	
	
	private void setValues()
	{
		card1[m_iCurrentCard].keyword1 = m_txfKwd1.getText();
		card1[m_iCurrentCard].keyword2 = m_txfKwd2.getText();
		
		String stringb = new String();
		String stringc = new String();
		
		stringb = card1[m_iCurrentCard].keyword1;
		stringc = card1[m_iCurrentCard].keyword2;
			
		StringBuffer sbTemp = new StringBuffer();
		
		boolean done = false;
		
		int i = 0;//current position of checking
		int j = 0;//num words in textArea
		
		char cTemp = 'x';
		
		String sTemp = new String(m_txaInfo.getText());
		
		do
		{	
			cTemp = sTemp.charAt(i);
			
			if(cTemp == ' ')
			{
				i++;
			}
			else
			{
				do 
				{
					sbTemp.append(cTemp);
					
					i++;
					
					if(i > sTemp.length() - 1)
					{
						done = true;
						cTemp = ' ';
					}
					else
					{
						cTemp = sTemp.charAt(i);
					}
				}
				while(cTemp != ' ');
			
				card1[m_iCurrentCard].text[j] = sbTemp.toString();
				
				sbTemp = new StringBuffer();
				
				j++;
			}
		}
		while(!done);
	
		card1[m_iCurrentCard].numWords = j;
	}
	
	
	
	private int errorCheck()
	{
		String sTemp = new String(m_txaInfo.getText());
		
		if(sTemp == null)//check for empty card
		{
			displayMessage("No data was entered.");
			
			return 0;	
		}
				
		for(int iChar = 0; iChar < sTemp.length(); iChar++)
		{
			if(sTemp.charAt(iChar) == '/')//check for syntax error
			{
				displayMessage("You can't use a /.");
				
				return 0;
			}
		}	
		
		return 1;
	}
	
	
	
	private void doSaveToArray()
	{
		if(errorCheck() == 0)
		{
			return;	
		}
		   
		setValues();
		
		m_iNumCards++;
	}
	
	
	
	private void doNewCard()
	{
		displayMessage("Status:");
		
		m_txaInfo.setText("");
	
		m_txfKwd1.setText("");		
		m_txfKwd2.setText("");
		
		m_iCurrentCard = m_iNumCards;
		m_lblCardNum.setText("Card Number: " + m_iCurrentCard);
	}
	
	
	
	private void initializeSearch()
	{
		iNumFinds = 0;
		iNumCurrentValidCardIndex = 0;
		iNumFinds2 = 0;
		m_iNumIndex = new int[50];
		iNumIndex2 = new int[25];
	}
	
	
	
	private void searchKwd()
	{
		initializeSearch();
		
		String sKwd1 = new String(m_txfKwd1S.getText().trim());
		String sKwd2 = new String(m_txfKwd2S.getText().trim());
		
		StringBuffer sbTemp = new StringBuffer();
		
		if(sKwd1.length() < 1)//check for type of search one kwd or both
		{
			displayMessage("You need a keyword.");	
		
			return;
		}
		else
		{
			if(sKwd2.length() < 1)
			{
				searchKwd1Only = true;
			}
			else
			{
				searchKwd1Only = false;	
			}
		}
		
		if(searchKwd1Only == true)
		{
			for(int i = 0; i < m_iNumCards; i++)
			{
				if(card1[i].keyword1.equals(sKwd1))
				{
					m_iNumIndex[iNumFinds] = i;
					iNumFinds++;
				}
			}
			
			if(iNumFinds == 0)
			{
				displayMessage("No matches found.");
				
				return;	
			}
			
			m_lblMessage.setText("Displaying match 1 of " + iNumFinds);
		
									
			for(int i = 0; i < card1[m_iNumIndex[0]].numWords; i++)
			{
				sbTemp.append(card1[m_iNumIndex[0]].text[i]);
				sbTemp.append(" ");
			}
			
			m_txaInfo.setText(sbTemp.toString());
			m_txfKwd1.setText(card1[m_iNumIndex[0]].keyword1);
			m_txfKwd2.setText(card1[m_iNumIndex[0]].keyword2);
			m_iCurrentCard = m_iNumIndex[0];
			m_lblCardNum.setText("Card Number: " + (m_iCurrentCard + 1));//updates the current card label
		}
		else
		{
			for(int i = 0; i < m_iNumCards; i++)
			{
				if(card1[i].keyword1.equals(sKwd1))
				{
					m_iNumIndex[iNumFinds] = i;
					iNumFinds++;
				}
			}
			
			for(int i = 0; i < iNumFinds + 1; i++)
			{
				if(card1[m_iNumIndex[i]].keyword2.equals(sKwd2))
				{
					iNumIndex2[iNumFinds2] = m_iNumIndex[i];
					iNumFinds2++;
				}
			}
			
			if(iNumFinds2 == 0)
			{
				displayMessage("No matches found.");
				
				return;	
			}
			
			m_lblMessage.setText("Displaying match 1 of " + iNumFinds2);
		
			for(int i = 0; i < card1[iNumIndex2[0]].numWords; i++)
			{
				sbTemp.append(card1[iNumIndex2[0]].text[i]);	
				sbTemp.append(" ");
			}	
			
			m_txaInfo.setText(sbTemp.toString());
			m_txfKwd1.setText(card1[iNumIndex2[0]].keyword1);
			m_txfKwd2.setText(card1[iNumIndex2[0]].keyword2);
			m_iCurrentCard = iNumIndex2[0];
			m_lblCardNum.setText("Card Number: " + (m_iCurrentCard + 1));//updates the current card label
		}
	}
	
	
	
	private void searchWord()
	{
		initializeSearch();
		
		String sWord = new String(m_txfText.getText());
		
		for(int i = 0; i < m_iNumCards; i++)//i is the card number
		{
			for(int i2 = 0; i2 < card1[i].numWords; i2++)//i2 is the word number
			{
				if(card1[i].text[i2].equals(sWord))	
				{
					m_iNumIndex[iNumFinds] = i;
					iNumFinds++;
					i2 = card1[i].numWords;
				}
			}
		}
		
		
		if(iNumFinds == 0)
		{
			displayMessage("No matches found.");
			
			return;	
		}
		
		m_lblMessage.setText("Displaying match 1 of " + iNumFinds);
		
		StringBuffer sbTemp = new StringBuffer();
		
		for(int i = 0; i < card1[m_iNumIndex[0]].numWords; i++)
		{
			sbTemp.append(card1[m_iNumIndex[0]].text[i]);	
			sbTemp.append(" ");
		}	
		
		m_txaInfo.setText(sbTemp.toString());
		m_txfKwd1.setText(card1[m_iNumIndex[0]].keyword1);
		m_txfKwd2.setText(card1[m_iNumIndex[0]].keyword2);
		m_iCurrentCard = m_iNumIndex[0];
		m_lblCardNum.setText("Card Number: " + (m_iCurrentCard + 1));//updates the current card label
	}
		
	
	
	private void nextResult()
	{
		StringBuffer sbTemp = new StringBuffer();
		
		if(searchKwd1Only = true)
		{
			if(iNumCurrentValidCardIndex + 1 < iNumFinds)
			{ 
				iNumCurrentValidCardIndex++;
				
				m_iCurrentCard = m_iNumIndex[iNumCurrentValidCardIndex];
			
				m_txfKwd1.setText(card1[m_iCurrentCard].keyword1);	
				m_txfKwd2.setText(card1[m_iCurrentCard].keyword2);	
				
				for(int i = 0; i < card1[m_iCurrentCard].numWords; i++)
				{
					sbTemp.append(card1[m_iCurrentCard].text[i]);	
					sbTemp.append(" ");
				}
				
				m_txaInfo.setText(sbTemp.toString());
			
				int iTemp = iNumCurrentValidCardIndex + 1;
				int iTemp1 = m_iCurrentCard + 1;
				
				displayMessage("Displaying match " + iTemp + " of " + iNumFinds);
								
				m_lblCardNum.setText("Card Number: " + iTemp1);
			}
		}
		else
		{
			if(iNumCurrentValidCardIndex + 1 < iNumFinds2)
			{
				iNumCurrentValidCardIndex++;
				
				m_iCurrentCard = iNumIndex2[iNumCurrentValidCardIndex];
			
				m_txfKwd1.setText(card1[m_iCurrentCard].keyword1);	
				m_txfKwd2.setText(card1[m_iCurrentCard].keyword2);	
				
				for(int i = 0; i < card1[m_iCurrentCard].numWords; i++)
				{
					sbTemp.append(card1[m_iCurrentCard].text[i]);	
					sbTemp.append(" ");
				}
				
				m_txaInfo.setText(sbTemp.toString());
				
				int iTemp = iNumCurrentValidCardIndex + 1;
				int iTemp1 = m_iCurrentCard + 1;
				
				displayMessage("Displaying match " + iTemp + " of " + iNumFinds2);
			
				m_lblCardNum.setText("Card Number: " + iTemp1);
			}
		}
	}
	
	
	
	private void previousResult()
	{
		StringBuffer sbTemp = new StringBuffer();
		
		if(searchKwd1Only = true)
		{
			if(iNumCurrentValidCardIndex > 0)
			{ 
				iNumCurrentValidCardIndex--;
				
				m_iCurrentCard = m_iNumIndex[iNumCurrentValidCardIndex];
			
				m_txfKwd1.setText(card1[m_iCurrentCard].keyword1);	
				m_txfKwd2.setText(card1[m_iCurrentCard].keyword2);	
				
				for(int i = 0; i < card1[m_iCurrentCard].numWords; i++)
				{
					sbTemp.append(card1[m_iCurrentCard].text[i]);	
					sbTemp.append(" ");
				}
				
				m_txaInfo.setText(sbTemp.toString());
			
				int iTemp = iNumCurrentValidCardIndex + 1;
				int iTemp1 = m_iCurrentCard + 1;
				
				displayMessage("Displaying match " + iTemp + " of " + iNumFinds);
								
				m_lblCardNum.setText("Card Number: " + iTemp1);
			}
		}
		else
		{
			if(m_iCurrentCard < iNumFinds2)
			{
				iNumCurrentValidCardIndex++;
				
				m_iCurrentCard = iNumIndex2[iNumCurrentValidCardIndex];
			
				m_txfKwd1.setText(card1[m_iCurrentCard].keyword1);	
				m_txfKwd2.setText(card1[m_iCurrentCard].keyword2);	
				
				for(int i = 0; i < card1[m_iCurrentCard].numWords; i++)
				{
					sbTemp.append(card1[m_iCurrentCard].text[i]);	
					sbTemp.append(" ");
				}
				
				m_txaInfo.setText(sbTemp.toString());
			
				displayMessage("Displaying match " + iNumCurrentValidCardIndex + " of " + iNumFinds2);
			
				m_lblCardNum.setText("Card Number: " + m_iCurrentCard);
			}
		}
	}
	
	
	
	private void displayMessage(String s)
	{
		m_lblMessage.setText(s);
	}
	
	
	
	public void itemStateChanged(ItemEvent ie)
	{
			
	}
	
	
	
	public void actionPerformed(ActionEvent ae)
	{
		Object objSource = ae.getSource();
		
		if(objSource == m_btnSaveCard)
		{
			doSaveToArray();
			doSaveToDisk();
		}
		else if(objSource == m_btnNewCard)
		{
			doNewCard();	
		}
		else if(objSource == m_btnSearchKwd)
		{
			searchKwd();
		}
		else if(objSource == m_btnSearchText)
		{
			searchWord();
		}
		else if(objSource == m_btnNextResult)
		{
			nextResult();
		}
		else if(objSource == m_btnPreviousResult)	
		{
			previousResult();
		}
		else if(objSource == mniClose)
		{
			System.exit(0);		
		}
		else if(objSource == mniNewCard)
		{
			doNewCard();
		}
		else if(objSource == mniSave)
		{
			doSaveToArray();
			doSaveToDisk();
		}
		else if(objSource == cbx600x500)
		{
			this.setSize(600,500);
		}
		else if(objSource == cbx600x400)
		{
			this.setSize(600,400);
		}
		else if(objSource == cbx500x400)
		{
			this.setSize(500,400);
		}
		else if(objSource == cbx500x300)
		{
			this.setSize(500,300);
		}
		else if(objSource == cbx400x300)
		{
			this.setSize(400,300);
		}
		else if(objSource == cbx400x200)
		{
			this.setSize(400,200);
		}
	}
	
	
	
	private void initializeCard()
	{
		//setting up text of first card for textfield///////
		StringBuffer sbTemp = new StringBuffer();
		
		for(int i = 0; i < card1[0].numWords; i++)
		{
			sbTemp.append(card1[0].text[i]);
			sbTemp.append(" ");
		}
		/////////////////////////////////////////////////////
		
		m_txfKwd1.setText("" + card1[0].keyword1);
		m_txfKwd2.setText("" + card1[0].keyword2);
		m_txaInfo.setText(sbTemp.toString());
	}
	
	
	
	public void windowActivated(WindowEvent we)
	{
		
	}
	
	public void windowClosed(WindowEvent we)
	{
		System.exit(0);
	}
	
	public void windowClosing(WindowEvent we)
	{
		this.setVisible(false);
		this.dispose();
	}
	
	public void windowDeactivated(WindowEvent we)
	{
		
	}
	
	public void windowDeiconified(WindowEvent we)
	{
		
	}
	
	public void windowIconified(WindowEvent we)
	{
		
	}
	
	public void windowOpened(WindowEvent we)
	{
		doReadData();
		initializeCard();
	}
}



class DataCard1 implements Serializable 
{
	public String text[];
	
	public String keyword1 = new String();
	public String keyword2 = new String();
	
	public int numWords = 0;
	
	public boolean hasKeyword = false;
	

	
	public DataCard1()
	{
		text = new String[250];		
	}
}
