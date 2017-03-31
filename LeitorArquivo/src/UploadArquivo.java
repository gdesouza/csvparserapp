
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.Font;


public class UploadArquivo extends JFrame{

	private JPanel contentPane;
	private JFileChooser fc;
	private JFrame _this = this;
	private JLabel lblArquivo;
	private JButton btnProcessar;
	private File file;
	private static final String defaultPath = "src\\files\\";
	private static final String defaultFileName = "arquivo.csv";


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UploadArquivo frame = new UploadArquivo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UploadArquivo() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Leitor.class.getResource("/img/file.png")));
		setTitle("Leitor de Arquivo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lblIcone = new JLabel("");
		lblIcone.setIcon(new ImageIcon(Leitor.class.getResource("/img/file.png")));
		lblIcone.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblIcone = new GridBagConstraints();
		gbc_lblIcone.insets = new Insets(20, 0, 5, 0);
		gbc_lblIcone.gridx = 0;
		gbc_lblIcone.gridy = 0;
		contentPane.add(lblIcone, gbc_lblIcone);

		JLabel lblCarregueArquivo = new JLabel("Carregue a arquivo a ser lido");
		lblCarregueArquivo.setFont(new Font("Century Gothic", Font.BOLD, 15));
		GridBagConstraints gbc_lblCarregueArquivo = new GridBagConstraints();
		gbc_lblCarregueArquivo.insets = new Insets(10, 0, 5, 0);
		gbc_lblCarregueArquivo.gridx = 0;
		gbc_lblCarregueArquivo.gridy = 1;
		contentPane.add(lblCarregueArquivo, gbc_lblCarregueArquivo);

		Panel pFileChooser = new Panel();
		GridBagConstraints gbc_pFileChooser = new GridBagConstraints();
		gbc_pFileChooser.insets = new Insets(0, 0, 5, 0);
		gbc_pFileChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_pFileChooser.gridx = 0;
		gbc_pFileChooser.gridy = 2;
		contentPane.add(pFileChooser, gbc_pFileChooser);
		GridBagLayout gbl_pFileChooser = new GridBagLayout();
		gbl_pFileChooser.columnWidths = new int[]{0, 0};
		gbl_pFileChooser.rowHeights = new int[]{25, 0, 0};
		gbl_pFileChooser.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pFileChooser.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		pFileChooser.setLayout(gbl_pFileChooser);

		fc = new JFileChooser();
		JButton btnSelecionarArquivo = new JButton("Selecione um arquivo...");
		btnSelecionarArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(_this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					lblArquivo.setText("Arquivo selecionado: " + file.getName());
					btnProcessar.setEnabled(true);
				}else{
					file = null;
					lblArquivo.setText("");
					btnProcessar.setEnabled(false);
				}
			}
		});
		
		lblArquivo = new JLabel("");
		GridBagConstraints gbc_lblArquivo = new GridBagConstraints();
		gbc_lblArquivo.insets = new Insets(0, 0, 5, 0);
		gbc_lblArquivo.gridx = 0;
		gbc_lblArquivo.gridy = 0;
		pFileChooser.add(lblArquivo, gbc_lblArquivo);
		GridBagConstraints gbc_btnSelecionarArquivo = new GridBagConstraints();
		gbc_btnSelecionarArquivo.gridx = 0;
		gbc_btnSelecionarArquivo.gridy = 1;
		pFileChooser.add(btnSelecionarArquivo, gbc_btnSelecionarArquivo);
		
		btnProcessar = new JButton("Processar");
		btnProcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Files.copy(file.toPath(), new FileOutputStream(new File(defaultPath+defaultFileName).getCanonicalFile()));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					Leitor leitor = new Leitor(new File(defaultPath+defaultFileName).getCanonicalFile());
					leitor.readFile();
					System.out.println(leitor.getAtArrayIndex(leitor.getInfoList().get(1), 2));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnProcessar.setEnabled(false);
		GridBagConstraints gbc_btnProcessar = new GridBagConstraints();
		gbc_btnProcessar.insets = new Insets(0, 0, 15, 0);
		gbc_btnProcessar.gridx = 0;
		gbc_btnProcessar.gridy = 3;
		contentPane.add(btnProcessar, gbc_btnProcessar);
		setLocationRelativeTo(null);
	}

}
