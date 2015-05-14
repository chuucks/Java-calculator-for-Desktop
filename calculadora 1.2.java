package calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Ventana
  extends JFrame
  implements ActionListener, KeyListener
{
  private boolean señal = true;
  private boolean semaforo_escritura = false;
  private boolean guardado_memoria = false;
  private boolean validador_operaciones = false;
  private boolean decide = true;
  private boolean shiftIsPressed = false;
  private boolean escritor = false;
  private boolean ctrlIsPressed = false;
  private boolean resultadoIsActive = false;
  private String lectura1 = "";
  private String lectura2 = "";
  private String valor_memoria = "0";
  private String operacion = "";
  private JMenuBar barra;
  private JMenu menu_ver;
  private JMenu cambia_skins;
  private JMenu menu_edicion;
  private JMenu menu_ayuda;
  private JMenuItem skin_clasico;
  private JMenuItem skin_pro;
  private JMenuItem skin_pink;
  private JMenuItem skin_da;
  private JMenuItem edicion_copiar;
  private JMenuItem edicion_pegar;
  private JMenuItem ver_ayuda;
  private JMenuItem acerca_de;
  private Clipboard portapapeles;
  private JTextArea pantalla;
  private JTextArea mensaje;
  private JTextArea pantalla_resultado;
  private JScrollPane deslizable1;
  private JScrollPane deslizable2;
  private JScrollPane deslizable3;
  private JPanel superior;
  private JPanel memoria;
  private JPanel botones;
  private JPanel funciones;
  private JButton limpiar_memoria;
  private JButton mostrar_memoria;
  private JButton establecer_memoria;
  private JButton sumar_memoria;
  private JButton restar_memoria;
  private JButton igual;
  private JButton punto;
  private JButton cero;
  private JButton uno;
  private JButton dos;
  private JButton tres;
  private JButton cuatro;
  private JButton cinco;
  private JButton seis;
  private JButton siete;
  private JButton ocho;
  private JButton nueve;
  private JButton borrar;
  private JButton atras;
  private JButton negar;
  private JButton mas;
  private JButton menos;
  private JButton por;
  private JButton entre;
  private JButton raiz;
  private Container general;
  private float resultado;
  private StringBuilder remplazador;
  
  public Ventana()
  {
    super("D.A. Calculator");
    setResizable(false);
    setSize(310, 360);
    setLayout(new GridBagLayout());
    setDefaultCloseOperation(3);
    
    addKeyListener(this);
    
    this.portapapeles = getToolkit().getSystemClipboard();
    
    this.barra = new JMenuBar();
    
    this.menu_ver = new JMenu("Ver");
    this.menu_ver.setMnemonic(86);
    this.cambia_skins = new JMenu("Cambiar Skins");
    this.skin_clasico = new JMenuItem("Clasico");
    this.skin_clasico.addActionListener(new Skinclas());
    this.skin_da = new JMenuItem("Dream Apps");
    this.skin_da.addActionListener(new Skinda());
    this.skin_pro = new JMenuItem("Pro");
    this.skin_pro.addActionListener(new Skinpro());
    this.skin_pink = new JMenuItem("Pink");
    this.skin_pink.addActionListener(new Skinpink());
    this.cambia_skins.add(this.skin_clasico);
    this.cambia_skins.add(this.skin_da);
    this.cambia_skins.add(this.skin_pro);
    this.cambia_skins.add(this.skin_pink);
    this.menu_ver.add(this.cambia_skins);
    this.barra.add(this.menu_ver);
    
    this.menu_edicion = new JMenu("Edición");
    this.menu_edicion.setMnemonic(69);
    this.edicion_copiar = new JMenuItem("Copiar        Ctrl+C");
    this.edicion_copiar.addActionListener(new Copiar());
    this.edicion_pegar = new JMenuItem("Pegar         Ctrl+V");
    this.edicion_pegar.addActionListener(new Pegar());
    this.menu_edicion.add(this.edicion_copiar);
    this.menu_edicion.add(this.edicion_pegar);
    
    this.menu_ayuda = new JMenu("Ayuda");
    this.menu_ayuda.setMnemonic(65);
    this.ver_ayuda = new JMenuItem("Ver ayuda");
    this.ver_ayuda.addActionListener(new Ayuda());
    this.acerca_de = new JMenuItem("Acerca de la calculadora");
    this.acerca_de.addActionListener(new Acerca());
    this.menu_ayuda.add(this.ver_ayuda);
    this.menu_ayuda.addSeparator();
    this.menu_ayuda.add(this.acerca_de);
    
    this.barra.add(this.menu_edicion);
    this.barra.add(this.menu_ayuda);
    setJMenuBar(this.barra);
    
    this.general = new Container();
    this.general.setLayout(new BorderLayout());
    
    this.pantalla = new JTextArea("", 5, 10);
    this.pantalla.setEditable(false);
    Font font1 = new Font("Arial", 1, 16);
    this.pantalla.setFont(font1);
    this.pantalla.setForeground(Color.GREEN);
    this.deslizable1 = new JScrollPane(this.pantalla);
    this.superior = new JPanel(new BorderLayout());
    this.superior.add(this.deslizable1, "North");
    
    this.mensaje = new JTextArea("", 2, 11);
    this.mensaje.setEditable(false);
    Font font2 = new Font("Arial", 1, 15);
    this.mensaje.setFont(font2);
    this.mensaje.setForeground(Color.MAGENTA);
    this.deslizable2 = new JScrollPane(this.mensaje);
    this.superior.add(this.deslizable2, "Before");
    
    this.pantalla_resultado = new JTextArea("", 2, 11);
    Font font3 = new Font("Arial", 1, 15);
    this.pantalla_resultado.setFont(font3);
    this.pantalla_resultado.setForeground(Color.RED);
    this.deslizable3 = new JScrollPane(this.pantalla_resultado);
    this.pantalla_resultado.setText("");
    this.superior.add(this.deslizable3, "After");
    

    this.memoria = new JPanel(new GridLayout(1, 4, 2, 2));
    
    this.limpiar_memoria = new JButton("Mc");
    this.mostrar_memoria = new JButton("Mr");
    this.establecer_memoria = new JButton("Ms");
    this.sumar_memoria = new JButton("M+");
    this.restar_memoria = new JButton("M-");
    
    this.limpiar_memoria.addActionListener(this);
    this.mostrar_memoria.addActionListener(this);
    this.establecer_memoria.addActionListener(this);
    this.sumar_memoria.addActionListener(this);
    this.restar_memoria.addActionListener(this);
    
    this.memoria.add(this.limpiar_memoria);
    this.memoria.add(this.mostrar_memoria);
    this.memoria.add(this.establecer_memoria);
    this.memoria.add(this.sumar_memoria);
    this.memoria.add(this.restar_memoria);
    
    this.general.add(this.superior, "North");
    this.superior.add(this.memoria, "South");
    

    this.punto = new JButton(".");
    this.cero = new JButton("0");
    this.uno = new JButton("1");
    this.dos = new JButton("2");
    this.tres = new JButton("3");
    this.cuatro = new JButton("4");
    this.cinco = new JButton("5");
    this.seis = new JButton("6");
    this.siete = new JButton("7");
    this.ocho = new JButton("8");
    this.nueve = new JButton("9");
    this.raiz = new JButton("√");
    
    this.punto.addActionListener(this);
    this.cero.addActionListener(this);
    this.uno.addActionListener(this);
    this.dos.addActionListener(this);
    this.tres.addActionListener(this);
    this.cuatro.addActionListener(this);
    this.cinco.addActionListener(this);
    this.seis.addActionListener(this);
    this.siete.addActionListener(this);
    this.ocho.addActionListener(this);
    this.nueve.addActionListener(this);
    this.raiz.addActionListener(this);
    
    this.botones = new JPanel(new GridLayout(4, 3, 9, 5));
    
    this.botones.add(this.siete);
    this.botones.add(this.ocho);
    this.botones.add(this.nueve);
    this.botones.add(this.cuatro);
    this.botones.add(this.cinco);
    this.botones.add(this.seis);
    this.botones.add(this.uno);
    this.botones.add(this.dos);
    this.botones.add(this.tres);
    this.botones.add(this.cero);
    this.botones.add(this.punto);
    this.botones.add(this.raiz);
    
    this.general.add(this.botones, "Before");
    
    this.borrar = new JButton("C");
    this.atras = new JButton("←");
    this.negar = new JButton("+/-");
    this.mas = new JButton("+");
    this.menos = new JButton("-");
    this.por = new JButton("*");
    this.entre = new JButton("/");
    this.igual = new JButton("=");
    
    this.borrar.addActionListener(this);
    this.atras.addActionListener(this);
    this.negar.addActionListener(this);
    this.mas.addActionListener(this);
    this.menos.addActionListener(this);
    this.por.addActionListener(this);
    this.entre.addActionListener(this);
    this.igual.addActionListener(this);
    
    this.funciones = new JPanel(new GridLayout(4, 2, 6, 5));
    
    this.funciones.add(this.borrar);
    this.funciones.add(this.atras);
    this.funciones.add(this.negar);
    this.funciones.add(this.entre);
    this.funciones.add(this.por);
    this.funciones.add(this.menos);
    this.funciones.add(this.mas);
    this.funciones.add(this.igual);
    
    this.general.add(this.funciones, "After");
    
    GridBagConstraints gbc = new GridBagConstraints();
    
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.gridwidth = 3;
    gbc.gridheight = 3;
    gbc.weightx = 0.0D;
    gbc.weighty = 0.0D;
    
    add(this.general, gbc);
    setVisible(true);
  }
  
  class Skinclas
    implements ActionListener
  {
    Skinclas() {}
    
    public void actionPerformed(ActionEvent e)
    {
      Ventana.this.colorearOperaciones(Color.LIGHT_GRAY, Color.BLUE);
      Ventana.this.colorearNumeros(Color.LIGHT_GRAY, Color.BLUE);
      Ventana.this.colorearMemoria(Color.GRAY, Color.BLUE);
      Ventana.this.pantalla.setForeground(Color.BLUE);
      Ventana.this.mensaje.setForeground(Color.BLUE);
    }
  }
  
  class Skinda
    implements ActionListener
  {
    Skinda() {}
    
    public void actionPerformed(ActionEvent e)
    {
      Ventana.this.colorearOperaciones(Color.MAGENTA, Color.BLACK);
      Ventana.this.colorearNumeros(Color.GREEN, Color.BLACK);
      Ventana.this.colorearMemoria(Color.BLUE, Color.WHITE);
      Ventana.this.pantalla.setForeground(Color.GREEN);
      Ventana.this.mensaje.setForeground(Color.MAGENTA);
    }
  }
  
  class Skinpro
    implements ActionListener
  {
    Skinpro() {}
    
    public void actionPerformed(ActionEvent e)
    {
      Ventana.this.colorearOperaciones(Color.BLACK, Color.WHITE);
      Ventana.this.colorearNumeros(Color.BLACK, Color.WHITE);
      Ventana.this.colorearMemoria(Color.DARK_GRAY, Color.WHITE);
      Ventana.this.pantalla.setForeground(Color.BLACK);
      Ventana.this.mensaje.setForeground(Color.BLACK);
    }
  }
  
  class Skinpink
    implements ActionListener
  {
    Skinpink() {}
    
    public void actionPerformed(ActionEvent e)
    {
      Ventana.this.colorearOperaciones(Color.GRAY, Color.PINK);
      Ventana.this.colorearNumeros(Color.PINK, Color.BLACK);
      Ventana.this.colorearMemoria(Color.MAGENTA, Color.WHITE);
      Ventana.this.pantalla.setForeground(Color.MAGENTA);
      Ventana.this.mensaje.setForeground(Color.MAGENTA);
    }
  }
  
  class Copiar
    implements ActionListener
  {
    Copiar() {}
    
    public void actionPerformed(ActionEvent e)
    {
      if ((Ventana.this.señal) && (!Ventana.this.lectura1.equals(""))) {
        Ventana.this.portapapeles.setContents(new StringSelection(Ventana.this.lectura1), null);
      } else if (!Ventana.this.lectura2.equals("")) {
        Ventana.this.portapapeles.setContents(new StringSelection(Ventana.this.lectura2), null);
      } else if (!Ventana.this.lectura1.equals("")) {
        Ventana.this.portapapeles.setContents(new StringSelection(Ventana.this.lectura1), null);
      } else {
        Ventana.this.portapapeles.setContents(new StringSelection("0"), null);
      }
    }
  }
  
  class Pegar
    implements ActionListener
  {
    Pegar() {}
    
    public void actionPerformed(ActionEvent e)
    {
      Transferable data = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
      if ((data != null) && (data.isDataFlavorSupported(DataFlavor.stringFlavor))) {
        try
        {
          Ventana.this.lectura1 = ((String)data.getTransferData(DataFlavor.stringFlavor));
          char[] caracteres = Ventana.this.lectura1.toCharArray();
          boolean valida_caracteres = true;
          for (char c : caracteres) {
            if (Character.isLetter(c)) {
              valida_caracteres = false;
            }
          }
          if (valida_caracteres) {
            Ventana.this.pantalla.setText(Ventana.this.lectura1);
          }
        }
        catch (UnsupportedFlavorException ex)
        {
          Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
          Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
  }
  
  class Ayuda
    implements ActionListener
  {
    Ayuda() {}
    
    public void actionPerformed(ActionEvent e)
    {
      JOptionPane.showMessageDialog(null, " Como usar D.A. Calculator\n\n  1.- Usar los botones en ventana\n       -Numeros:\n         Ingresar los numeros desde los botones en la ventana\n       -Operaciones:\n         Ingresar las operaciones desde los botones en la ventana\n       -Borrado:\n         Click en los botones rojos para el borrado parcial o total de la pantalla\n\n  2.- Ingresar desde teclado\n        -Numeros:\n         Teclar numeros desde teclado del 0 al 9 o combinaciones de ellos\n        -Operaciones:\n         '+' = '+'\n         '-' = '-'\n         '*' = 'shift' + '+'\n         '/' = 'shift' + '7'\n         '+/-' = 'shift' + '-'\n         '=' = 'enter'\n         'copy' = 'ctrl' + 'C'\n         'paste' = 'ctrl' + 'V'\n         'ayuda' = 'F1'\n\n  3.- Opciones de memoria\n       'Mc' = borrar memoria\n       'Mr' = mostrar valor en pantalla\n       'Ms' = guardar valor en memoria\n       'M+' = sumar valor a la memoria\n       'M-' = restar valor a la memoria\n\n  4.- Opciones de edición\n      Operaciones del portapapeles desde el menu 'Edición'\n\n  5.- Cambio de skins\n       Elejir el skin preferido desde el menu 'Ver' + 'Cambiar skin'\n ", "Dream Apps Help", -1);
    }
  }
  
  class Acerca
    implements ActionListener
  {
    Acerca() {}
    
    public void actionPerformed(ActionEvent e)
    {
      JOptionPane.showMessageDialog(null, "            DREAM APPS CALCULATOR\n\n Dream apps\n Version 1.2\n México DF  Julio 2014\n Desarrollador Carlos Salazar\n\n Contacto:\n   dream_apps@outlook.com\n   dreamappsmx@gmail.com\n   @DreamAppsMx\n  /DreamAppsMx\n ", "Acerca de D.A. Calculator version 1.2", -1);
    }
  }
  
  public void actionPerformed(ActionEvent evento)
  {
    JButton boton = (JButton)evento.getSource();
    borradoPantalla();
    switch (boton.getText())
    {
    case "Mc": 
      this.valor_memoria = "0";
      this.guardado_memoria = false;
      break;
    case "Mr": 
      this.lectura1 = this.valor_memoria;
      this.pantalla.setText(this.lectura1);
      break;
    case "Ms": 
      if (this.señal)
      {
        if ((!this.lectura1.equals("")) && (!this.lectura1.equals("0")) && (!this.lectura1.equals(".")) && (!this.lectura1.equals("0.0")) && (!this.lectura1.equals(".0")))
        {
          this.valor_memoria = "";
          this.valor_memoria = this.lectura1;
          this.guardado_memoria = true;
        }
      }
      else if ((!this.lectura2.equals("")) && (!this.lectura2.equals("0")) && (!this.lectura1.equals(".")) && (!this.lectura1.equals("0.0")) && (!this.lectura1.equals(".0")))
      {
        this.valor_memoria = "";
        this.valor_memoria = this.lectura2;
        this.guardado_memoria = true;
      }
      break;
    case "M+": 
      String apoyo_mas = this.lectura1;
      this.lectura2 = this.lectura1;
      this.lectura1 = this.valor_memoria;
      this.operacion = "+";
      operaResultados();
      this.valor_memoria = this.lectura1;
      this.lectura1 = apoyo_mas;
      this.pantalla.setText(this.lectura1);
      this.guardado_memoria = true;
      break;
    case "M-": 
      String apoyo_menos = this.lectura1;
      this.lectura2 = this.lectura1;
      this.lectura1 = this.valor_memoria;
      this.operacion = "-";
      operaResultados();
      this.valor_memoria = this.lectura1;
      this.lectura1 = apoyo_menos;
      this.pantalla.setText(this.lectura1);
      this.guardado_memoria = true;
      break;
    case "C": 
      this.lectura1 = "";
      this.lectura2 = "";
      this.resultado = 0.0F;
      this.operacion = "";
      this.señal = true;
      this.pantalla.setText("");
      this.pantalla_resultado.setText("");
      break;
    case "←": 
      if (this.señal)
      {
        if (!this.lectura1.equals(""))
        {
          this.remplazador = new StringBuilder(this.lectura1);
          this.remplazador.deleteCharAt(this.remplazador.length() - 1);
          this.lectura1 = this.remplazador.toString();
          if (this.lectura1.equals("-")) {
            this.lectura1 = "";
          }
        }
      }
      else if (!this.lectura2.equals(""))
      {
        this.remplazador = new StringBuilder(this.lectura2);
        this.remplazador.deleteCharAt(this.remplazador.length() - 1);
        this.lectura2 = this.remplazador.toString();
        if (this.lectura2.equals("-")) {
          this.lectura2 = "";
        }
      }
      else
      {
        this.operacion = "";
        this.señal = true;
      }
      this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
      break;
    case "+/-": 
      if (this.señal)
      {
        if (!this.lectura1.equals("")) {
          if (this.lectura1.contains("-"))
          {
            this.remplazador = new StringBuilder(this.lectura1);
            this.remplazador.deleteCharAt(0);
            this.lectura1 = this.remplazador.toString();
          }
          else
          {
            this.remplazador = new StringBuilder(this.lectura1);
            this.remplazador.insert(0, "-");
            this.lectura1 = this.remplazador.toString();
          }
        }
      }
      else if (!this.lectura2.equals(""))
      {
        if (this.lectura2.contains("-"))
        {
          this.remplazador = new StringBuilder(this.lectura2);
          this.remplazador.deleteCharAt(0);
          this.lectura2 = this.remplazador.toString();
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura2);
          this.remplazador.insert(0, "-");
          this.lectura2 = this.remplazador.toString();
        }
      }
      else if (!this.lectura1.equals("")) {
        if (this.lectura1.contains("-"))
        {
          this.remplazador = new StringBuilder(this.lectura1);
          this.remplazador.deleteCharAt(0);
          this.lectura1 = this.remplazador.toString();
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura1);
          this.remplazador.insert(0, "-");
          this.lectura1 = this.remplazador.toString();
        }
      }
      this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
      break;
    case ".": 
      if (this.señal)
      {
        if (!this.lectura1.contains(".")) {
          if (this.escritor)
          {
            this.lectura1 = ".";
            this.pantalla.setText(".");
            this.escritor = false;
          }
          else
          {
            this.lectura1 += ".";
            this.pantalla.append(".");
          }
        }
      }
      else if (!this.lectura2.contains("."))
      {
        this.lectura2 += ".";
        this.pantalla.append(".");
      }
      break;
    case "0": 
      if (this.señal)
      {
        if (!this.lectura1.equals("0")) {
          if (this.escritor)
          {
            this.lectura1 = "0";
            this.pantalla.setText("0");
            this.pantalla_resultado.setText("");
            this.escritor = false;
          }
          else
          {
            this.lectura1 += "0";
            this.pantalla.append("0");
          }
        }
      }
      else if (!this.lectura2.equals("0"))
      {
        this.lectura2 += "0";
        this.pantalla.append("0");
      }
      break;
    case "1": 
      if (this.señal)
      {
        if (!this.lectura1.equals("0"))
        {
          if (this.escritor)
          {
            this.lectura1 = "1";
            this.pantalla.setText("1");
            this.escritor = false;
          }
          else
          {
            this.lectura1 += "1";
            this.pantalla.append("1");
          }
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura1);
          this.remplazador.deleteCharAt(0);
          this.lectura1 = this.remplazador.toString();
          this.lectura1 += "1";
          this.pantalla.setText(this.lectura1);
        }
        this.pantalla_resultado.setText("");
      }
      else if (!this.lectura2.equals("0"))
      {
        this.lectura2 += "1";
        this.pantalla.append("1");
      }
      else
      {
        this.remplazador = new StringBuilder(this.lectura2);
        this.remplazador.deleteCharAt(0);
        this.lectura2 = this.remplazador.toString();
        this.lectura2 += "1";
        this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
      }
      break;
    case "2": 
      if (this.señal)
      {
        if (!this.lectura1.equals("0"))
        {
          if (this.escritor)
          {
            this.lectura1 = "2";
            this.pantalla.setText("2");
            this.escritor = false;
          }
          else
          {
            this.lectura1 += "2";
            this.pantalla.append("2");
          }
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura1);
          this.remplazador.deleteCharAt(0);
          this.lectura1 = this.remplazador.toString();
          this.lectura1 += "2";
          this.pantalla.setText(this.lectura1);
        }
        this.pantalla_resultado.setText("");
      }
      else if (!this.lectura2.equals("0"))
      {
        this.lectura2 += "2";
        this.pantalla.append("2");
      }
      else
      {
        this.remplazador = new StringBuilder(this.lectura2);
        this.remplazador.deleteCharAt(0);
        this.lectura2 = this.remplazador.toString();
        this.lectura2 += "2";
        this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
      }
      break;
    case "3": 
      if (this.señal)
      {
        if (!this.lectura1.equals("0"))
        {
          if (this.escritor)
          {
            this.lectura1 = "3";
            this.pantalla.setText("3");
            this.escritor = false;
          }
          else
          {
            this.lectura1 += "3";
            this.pantalla.append("3");
          }
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura1);
          this.remplazador.deleteCharAt(0);
          this.lectura1 = this.remplazador.toString();
          this.lectura1 += "3";
          this.pantalla.setText(this.lectura1);
        }
        this.pantalla_resultado.setText("");
      }
      else if (!this.lectura2.equals("0"))
      {
        this.lectura2 += "3";
        this.pantalla.append("3");
      }
      else
      {
        this.remplazador = new StringBuilder(this.lectura2);
        this.remplazador.deleteCharAt(0);
        this.lectura2 = this.remplazador.toString();
        this.lectura2 += "3";
        this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
      }
      break;
    case "4": 
      if (this.señal)
      {
        if (!this.lectura1.equals("0"))
        {
          if (this.escritor)
          {
            this.lectura1 = "4";
            this.pantalla.setText("4");
            this.escritor = false;
          }
          else
          {
            this.lectura1 += "4";
            this.pantalla.append("4");
          }
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura1);
          this.remplazador.deleteCharAt(0);
          this.lectura1 = this.remplazador.toString();
          this.lectura1 += "4";
          this.pantalla.setText(this.lectura1);
        }
        this.pantalla_resultado.setText("");
      }
      else if (!this.lectura2.equals("0"))
      {
        this.lectura2 += "4";
        this.pantalla.append("4");
      }
      else
      {
        this.remplazador = new StringBuilder(this.lectura2);
        this.remplazador.deleteCharAt(0);
        this.lectura2 = this.remplazador.toString();
        this.lectura2 += "4";
        this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
      }
      break;
    case "5": 
      if (this.señal)
      {
        if (!this.lectura1.equals("0"))
        {
          if (this.escritor)
          {
            this.lectura1 = "5";
            this.pantalla.setText("5");
            this.escritor = false;
          }
          else
          {
            this.lectura1 += "5";
            this.pantalla.append("5");
          }
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura1);
          this.remplazador.deleteCharAt(0);
          this.lectura1 = this.remplazador.toString();
          this.lectura1 += "5";
          this.pantalla.setText(this.lectura1);
        }
        this.pantalla_resultado.setText("");
      }
      else if (!this.lectura2.equals("0"))
      {
        this.lectura2 += "5";
        this.pantalla.append("5");
      }
      else
      {
        this.remplazador = new StringBuilder(this.lectura2);
        this.remplazador.deleteCharAt(0);
        this.lectura2 = this.remplazador.toString();
        this.lectura2 += "5";
        this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
      }
      break;
    case "6": 
      if (this.señal)
      {
        if (!this.lectura1.equals("0"))
        {
          if (this.escritor)
          {
            this.lectura1 = "6";
            this.pantalla.setText("6");
            this.escritor = false;
          }
          else
          {
            this.lectura1 += "6";
            this.pantalla.append("6");
          }
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura1);
          this.remplazador.deleteCharAt(0);
          this.lectura1 = this.remplazador.toString();
          this.lectura1 += "6";
          this.pantalla.setText(this.lectura1);
        }
        this.pantalla_resultado.setText("");
      }
      else if (!this.lectura2.equals("0"))
      {
        this.lectura2 += "6";
        this.pantalla.append("6");
      }
      else
      {
        this.remplazador = new StringBuilder(this.lectura2);
        this.remplazador.deleteCharAt(0);
        this.lectura2 = this.remplazador.toString();
        this.lectura2 += "6";
        this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
      }
      break;
    case "7": 
      if (this.señal)
      {
        if (!this.lectura1.equals("0"))
        {
          if (this.escritor)
          {
            this.lectura1 = "7";
            this.pantalla.setText("7");
            this.escritor = false;
          }
          else
          {
            this.lectura1 += "7";
            this.pantalla.append("7");
          }
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura1);
          this.remplazador.deleteCharAt(0);
          this.lectura1 = this.remplazador.toString();
          this.lectura1 += "7";
          this.pantalla.setText(this.lectura1);
        }
        this.pantalla_resultado.setText("");
      }
      else if (!this.lectura2.equals("0"))
      {
        this.lectura2 += "7";
        this.pantalla.append("7");
      }
      else
      {
        this.remplazador = new StringBuilder(this.lectura2);
        this.remplazador.deleteCharAt(0);
        this.lectura2 = this.remplazador.toString();
        this.lectura2 += "7";
        this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
      }
      break;
    case "8": 
      if (this.señal)
      {
        if (!this.lectura1.equals("0"))
        {
          if (this.escritor)
          {
            this.lectura1 = "8";
            this.pantalla.setText("8");
            this.escritor = false;
          }
          else
          {
            this.lectura1 += "8";
            this.pantalla.append("8");
          }
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura1);
          this.remplazador.deleteCharAt(0);
          this.lectura1 = this.remplazador.toString();
          this.lectura1 += "8";
          this.pantalla.setText(this.lectura1);
        }
        this.pantalla_resultado.setText("");
      }
      else if (!this.lectura2.equals("0"))
      {
        this.lectura2 += "8";
        this.pantalla.append("8");
      }
      else
      {
        this.remplazador = new StringBuilder(this.lectura2);
        this.remplazador.deleteCharAt(0);
        this.lectura2 = this.remplazador.toString();
        this.lectura2 += "8";
        this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
      }
      break;
    case "9": 
      if (this.señal)
      {
        if (!this.lectura1.equals("0"))
        {
          if (this.escritor)
          {
            this.lectura1 = "9";
            this.pantalla.setText("9");
            this.escritor = false;
          }
          else
          {
            this.lectura1 += "9";
            this.pantalla.append("9");
          }
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura1);
          this.remplazador.deleteCharAt(0);
          this.lectura1 = this.remplazador.toString();
          this.lectura1 += "9";
          this.pantalla.setText(this.lectura1);
        }
        this.pantalla_resultado.setText("");
      }
      else if (!this.lectura2.equals("0"))
      {
        this.lectura2 += "9";
        this.pantalla.append("9");
      }
      else
      {
        this.remplazador = new StringBuilder(this.lectura2);
        this.remplazador.deleteCharAt(0);
        this.lectura2 = this.remplazador.toString();
        this.lectura2 += "9";
        this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
      }
      break;
    case "+": 
      if (this.operacion.equals(""))
      {
        if (this.resultadoIsActive)
        {
          this.pantalla_resultado.setText("");
          this.pantalla.setText(this.lectura1);
        }
        this.pantalla.append("+");
        this.operacion = "+";
        this.señal = false;
      }
      else if (!this.lectura1.equals(""))
      {
        this.pantalla.setText(this.lectura1);
        operaResultados();
        if (!this.validador_operaciones)
        {
          this.operacion = "+";
          this.señal = false;
          this.pantalla.append("+");
        }
      }
      break;
    case "-": 
      if (this.operacion.equals(""))
      {
        if (this.resultadoIsActive)
        {
          this.pantalla_resultado.setText("");
          this.pantalla.setText(this.lectura1);
        }
        this.pantalla.append("-");
        this.operacion = "-";
        this.señal = false;
      }
      else if (!this.lectura1.equals(""))
      {
        this.pantalla.setText(this.lectura1);
        operaResultados();
        if (!this.validador_operaciones)
        {
          this.operacion = "-";
          this.señal = false;
          this.pantalla.append("-");
        }
      }
      break;
    case "*": 
      if (this.operacion.equals(""))
      {
        if (this.resultadoIsActive)
        {
          this.pantalla_resultado.setText("");
          this.pantalla.setText(this.lectura1);
        }
        this.pantalla.append("*");
        this.operacion = "*";
        this.señal = false;
      }
      else if (!this.lectura1.equals(""))
      {
        this.pantalla.setText(this.lectura1);
        operaResultados();
        if (!this.validador_operaciones)
        {
          this.operacion = "*";
          this.señal = false;
          this.pantalla.append("*");
        }
      }
      break;
    case "/": 
      if (this.operacion.equals(""))
      {
        if (this.resultadoIsActive)
        {
          this.pantalla_resultado.setText("");
          this.pantalla.setText(this.lectura1);
        }
        this.pantalla.append("/");
        this.operacion = "/";
        this.señal = false;
      }
      else if (!this.lectura1.equals(""))
      {
        this.pantalla.setText(this.lectura1);
        operaResultados();
        if (!this.validador_operaciones)
        {
          this.operacion = "/";
          this.señal = false;
          this.pantalla.append("/");
        }
      }
      break;
    case "√": 
      if ((!this.lectura1.equals("")) && (this.operacion.equals("")) && (this.lectura2.equals("")))
      {
        operacionRaiz();
      }
      else if ((!this.lectura1.equals("")) && (!this.operacion.equals("")))
      {
        operaResultados();
        operacionRaiz();
      }
      break;
    case "=": 
      operaResultados();
    }
    mensajeMemoria();
    requestFocus();
  }
  
  public void operaResultados()
  {
    if ((!this.lectura1.equals("")) && (!this.lectura1.equals(".")) && (!this.operacion.equals("")) && (!this.lectura2.equals("")) && (!this.lectura2.equals(".")))
    {
      this.resultado = operaNumeros(Float.parseFloat(this.lectura1), Float.parseFloat(this.lectura2), this.operacion);
      this.lectura1 = Float.toString(this.resultado);
      if (this.lectura1.contains(".0"))
      {
        this.remplazador = new StringBuilder(this.lectura1);
        this.remplazador.deleteCharAt(this.remplazador.length() - 1);
        this.remplazador.deleteCharAt(this.remplazador.length() - 1);
        this.lectura1 = this.remplazador.toString();
      }
      if (this.lectura1.contains("-0"))
      {
        this.remplazador = new StringBuilder(this.lectura1);
        this.remplazador.deleteCharAt(0);
        this.lectura1 = this.remplazador.toString();
      }
      if ((this.lectura1.equals("NaN")) || (this.lectura1.equals("Infinity")))
      {
        this.lectura1 = "";
        this.validador_operaciones = true;
      }
      this.pantalla_resultado.setText("" + this.lectura1);
      this.resultadoIsActive = true;
      this.lectura2 = "";
      this.operacion = "";
      this.resultado = 0.0F;
      this.señal = true;
    }
    else if ((!this.lectura1.equals("")) && (!this.lectura1.equals(".")) && (!this.operacion.equals("")) && (this.lectura2.equals("")))
    {
      this.resultado = operaNumeros(Float.parseFloat(this.lectura1), Float.parseFloat(this.lectura1), this.operacion);
      this.lectura1 = Float.toString(this.resultado);
      if (this.lectura1.contains(".0"))
      {
        this.remplazador = new StringBuilder(this.lectura1);
        this.remplazador.deleteCharAt(this.remplazador.length() - 1);
        this.remplazador.deleteCharAt(this.remplazador.length() - 1);
        this.lectura1 = this.remplazador.toString();
      }
      if (this.lectura1.contains("-0"))
      {
        this.remplazador = new StringBuilder(this.lectura1);
        this.remplazador.deleteCharAt(0);
        this.lectura1 = this.remplazador.toString();
      }
      if ((this.lectura1.equals("NaN")) || (this.lectura1.equals("Infinity")))
      {
        this.lectura1 = "";
        this.validador_operaciones = true;
      }
      this.pantalla_resultado.setText("" + this.lectura1);
      this.resultadoIsActive = true;
      this.lectura2 = "";
      this.operacion = "";
      this.resultado = 0.0F;
      this.señal = true;
    }
    else if ((this.lectura1.equals(".")) || (this.lectura2.equals(".")))
    {
      this.validador_operaciones = true;
      this.pantalla.setText("");
      this.escritor = true;
    }
    this.escritor = true;
  }
  
  public void operacionRaiz()
  {
    String clon = this.lectura1;
    if (!this.lectura1.equals("."))
    {
      double doble = Float.parseFloat(this.lectura1);
      doble = Math.sqrt(doble);
      this.lectura1 = Double.toString(doble);
      if (this.lectura1.contains(".0"))
      {
        this.remplazador = new StringBuilder(this.lectura1);
        this.remplazador.deleteCharAt(this.remplazador.length() - 1);
        this.remplazador.deleteCharAt(this.remplazador.length() - 1);
        this.lectura1 = this.remplazador.toString();
      }
      if ((this.lectura1.equals("NaN")) || (this.lectura1.equals("Infinity")))
      {
        this.validador_operaciones = true;
        this.lectura1 = "";
      }
      this.pantalla_resultado.setText(this.lectura1);
      this.resultadoIsActive = true;
      this.pantalla.setText("√" + clon);
      this.escritor = true;
    }
    else
    {
      this.lectura1 = "";
      this.pantalla.setText("");
      this.validador_operaciones = true;
    }
  }
  
  public float operaNumeros(float uno, float dos, String operacion)
  {
    float res_op = 0.0F;
    
    String str = operacion;int i = -1;
    switch (str.hashCode())
    {
    case 43: 
      if (str.equals("+")) {
        i = 0;
      }
      break;
    case 45: 
      if (str.equals("-")) {
        i = 1;
      }
      break;
    case 42: 
      if (str.equals("*")) {
        i = 2;
      }
      break;
    case 47: 
      if (str.equals("/")) {
        i = 3;
      }
      break;
    }
    switch (i)
    {
    case 0: 
      res_op = uno + dos;
      break;
    case 1: 
      res_op = uno - dos;
      break;
    case 2: 
      res_op = uno * dos;
      break;
    case 3: 
      res_op = uno / dos;
    }
    return res_op;
  }
  
  public void borradoPantalla()
  {
    if (this.semaforo_escritura)
    {
      this.pantalla.setText("");
      this.semaforo_escritura = false;
    }
  }
  
  public void mensajeMemoria()
  {
    if (this.guardado_memoria) {
      this.mensaje.setText("M");
    } else {
      this.mensaje.setText("");
    }
    if (this.validador_operaciones)
    {
      this.mensaje.append("\nOperacion invalida");
      this.validador_operaciones = false;
    }
  }
  
  public void keyPressed(KeyEvent tecla_presionada)
  {
    borradoPantalla();
    if (this.shiftIsPressed) {
      switch (tecla_presionada.getKeyCode())
      {
      case 521: 
        if (this.operacion.equals(""))
        {
          if (this.resultadoIsActive)
          {
            this.pantalla_resultado.setText("");
            this.pantalla.setText(this.lectura1);
          }
          this.pantalla.append("*");
          this.operacion = "*";
          this.señal = false;
        }
        else if (!this.lectura1.equals(""))
        {
          this.pantalla.setText(this.lectura1);
          operaResultados();
          if (!this.validador_operaciones)
          {
            this.operacion = "*";
            this.señal = false;
            this.pantalla.append("*");
          }
        }
        break;
      case 45: 
        if (this.señal)
        {
          if (!this.lectura1.equals("")) {
            if (this.lectura1.contains("-"))
            {
              this.remplazador = new StringBuilder(this.lectura1);
              this.remplazador.deleteCharAt(0);
              this.lectura1 = this.remplazador.toString();
            }
            else
            {
              this.remplazador = new StringBuilder(this.lectura1);
              this.remplazador.insert(0, "-");
              this.lectura1 = this.remplazador.toString();
            }
          }
        }
        else if (!this.lectura2.equals(""))
        {
          if (this.lectura2.contains("-"))
          {
            this.remplazador = new StringBuilder(this.lectura2);
            this.remplazador.deleteCharAt(0);
            this.lectura2 = this.remplazador.toString();
          }
          else
          {
            this.remplazador = new StringBuilder(this.lectura2);
            this.remplazador.insert(0, "-");
            this.lectura2 = this.remplazador.toString();
          }
        }
        else if (!this.lectura1.equals("")) {
          if (this.lectura1.contains("-"))
          {
            this.remplazador = new StringBuilder(this.lectura1);
            this.remplazador.deleteCharAt(0);
            this.lectura1 = this.remplazador.toString();
          }
          else
          {
            this.remplazador = new StringBuilder(this.lectura1);
            this.remplazador.insert(0, "-");
            this.lectura1 = this.remplazador.toString();
          }
        }
        this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
        break;
      case 55: 
        if (this.operacion.equals(""))
        {
          if (this.resultadoIsActive)
          {
            this.pantalla_resultado.setText("");
            this.pantalla.setText(this.lectura1);
          }
          this.pantalla.append("/");
          this.operacion = "/";
          this.señal = false;
        }
        else if (!this.lectura1.equals(""))
        {
          this.pantalla.setText(this.lectura1);
          operaResultados();
          if (!this.validador_operaciones)
          {
            this.operacion = "/";
            this.señal = false;
            this.pantalla.append("/");
          }
        }
        break;
      }
    } else if (this.ctrlIsPressed) {
      switch (tecla_presionada.getKeyCode())
      {
      case 67: 
        if ((this.señal) && (!this.lectura1.equals(""))) {
          this.portapapeles.setContents(new StringSelection(this.lectura1), null);
        } else if (!this.lectura2.equals("")) {
          this.portapapeles.setContents(new StringSelection(this.lectura2), null);
        } else if (!this.lectura1.equals("")) {
          this.portapapeles.setContents(new StringSelection(this.lectura1), null);
        } else {
          this.portapapeles.setContents(new StringSelection("0"), null);
        }
        break;
      case 86: 
        Transferable data = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        if ((data != null) && (data.isDataFlavorSupported(DataFlavor.stringFlavor))) {
          try
          {
            this.lectura1 = ((String)data.getTransferData(DataFlavor.stringFlavor));
            char[] caracteres = this.lectura1.toCharArray();
            boolean valida_caracteres = true;
            for (char c : caracteres) {
              if (Character.isLetter(c)) {
                valida_caracteres = false;
              }
            }
            if (valida_caracteres) {
              this.pantalla.setText(this.lectura1);
            }
          }
          catch (UnsupportedFlavorException ex)
          {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
          }
          catch (IOException ex)
          {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        break;
      }
    } else {
      switch (tecla_presionada.getKeyCode())
      {
      case 17: 
        this.ctrlIsPressed = true;
        break;
      case 16: 
        this.shiftIsPressed = true;
        break;
      case 112: 
        Ayuda ayuda = new Ayuda();
        ayuda.actionPerformed(null);
        break;
      case 8: 
        if (this.señal)
        {
          if (!this.lectura1.equals(""))
          {
            this.remplazador = new StringBuilder(this.lectura1);
            this.remplazador.deleteCharAt(this.remplazador.length() - 1);
            this.lectura1 = this.remplazador.toString();
            if (this.lectura1.equals("-")) {
              this.lectura1 = "";
            }
          }
        }
        else if (!this.lectura2.equals(""))
        {
          this.remplazador = new StringBuilder(this.lectura2);
          this.remplazador.deleteCharAt(this.remplazador.length() - 1);
          this.lectura2 = this.remplazador.toString();
          if (this.lectura2.equals("-")) {
            this.lectura2 = "";
          }
        }
        else
        {
          this.operacion = "";
          this.señal = true;
        }
        this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
        break;
      case 46: 
        if (this.señal)
        {
          if (!this.lectura1.contains(".")) {
            if (this.escritor)
            {
              this.lectura1 = ".";
              this.pantalla.setText(".");
              this.escritor = false;
            }
            else
            {
              this.lectura1 += ".";
              this.pantalla.append(".");
            }
          }
        }
        else if (!this.lectura2.contains("."))
        {
          this.lectura2 += ".";
          this.pantalla.append(".");
        }
        break;
      case 48: 
        if (this.señal)
        {
          if (!this.lectura1.equals("0")) {
            if (this.escritor)
            {
              this.lectura1 = "0";
              this.pantalla.setText("0");
              this.escritor = false;
            }
            else
            {
              this.lectura1 += "0";
              this.pantalla.append("0");
            }
          }
          this.pantalla_resultado.setText("");
        }
        else if (!this.lectura2.equals("0"))
        {
          this.lectura2 += "0";
          this.pantalla.append("0");
        }
        break;
      case 49: 
        if (this.señal)
        {
          if (!this.lectura1.equals("0"))
          {
            if (this.escritor)
            {
              this.lectura1 = "1";
              this.pantalla.setText("1");
              this.escritor = false;
            }
            else
            {
              this.lectura1 += "1";
              this.pantalla.append("1");
            }
          }
          else
          {
            this.remplazador = new StringBuilder(this.lectura1);
            this.remplazador.deleteCharAt(0);
            this.lectura1 = this.remplazador.toString();
            this.lectura1 += "1";
            this.pantalla.setText(this.lectura1);
          }
          this.pantalla_resultado.setText("");
        }
        else if (!this.lectura2.equals("0"))
        {
          this.lectura2 += "1";
          this.pantalla.append("1");
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura2);
          this.remplazador.deleteCharAt(0);
          this.lectura2 = this.remplazador.toString();
          this.lectura2 += "1";
          this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
        }
        break;
      case 50: 
        if (this.señal)
        {
          if (!this.lectura1.equals("0"))
          {
            if (this.escritor)
            {
              this.lectura1 = "2";
              this.pantalla.setText("2");
              this.escritor = false;
            }
            else
            {
              this.lectura1 += "2";
              this.pantalla.append("2");
            }
          }
          else
          {
            this.remplazador = new StringBuilder(this.lectura1);
            this.remplazador.deleteCharAt(0);
            this.lectura1 = this.remplazador.toString();
            this.lectura1 += "2";
            this.pantalla.setText(this.lectura1);
          }
          this.pantalla_resultado.setText("");
        }
        else if (!this.lectura2.equals("0"))
        {
          this.lectura2 += "2";
          this.pantalla.append("2");
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura2);
          this.remplazador.deleteCharAt(0);
          this.lectura2 = this.remplazador.toString();
          this.lectura2 += "2";
          this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
        }
        break;
      case 51: 
        if (this.señal)
        {
          if (!this.lectura1.equals("0"))
          {
            if (this.escritor)
            {
              this.lectura1 = "3";
              this.pantalla.setText("3");
              this.escritor = false;
            }
            else
            {
              this.lectura1 += "3";
              this.pantalla.append("3");
            }
          }
          else
          {
            this.remplazador = new StringBuilder(this.lectura1);
            this.remplazador.deleteCharAt(0);
            this.lectura1 = this.remplazador.toString();
            this.lectura1 += "3";
            this.pantalla.setText(this.lectura1);
          }
          this.pantalla_resultado.setText("");
        }
        else if (!this.lectura2.equals("0"))
        {
          this.lectura2 += "3";
          this.pantalla.append("3");
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura2);
          this.remplazador.deleteCharAt(0);
          this.lectura2 = this.remplazador.toString();
          this.lectura2 += "3";
          this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
        }
        break;
      case 52: 
        if (this.señal)
        {
          if (!this.lectura1.equals("0"))
          {
            if (this.escritor)
            {
              this.lectura1 = "4";
              this.pantalla.setText("4");
              this.escritor = false;
            }
            else
            {
              this.lectura1 += "4";
              this.pantalla.append("4");
            }
          }
          else
          {
            this.remplazador = new StringBuilder(this.lectura1);
            this.remplazador.deleteCharAt(0);
            this.lectura1 = this.remplazador.toString();
            this.lectura1 += "4";
            this.pantalla.setText(this.lectura1);
          }
          this.pantalla_resultado.setText("");
        }
        else if (!this.lectura2.equals("0"))
        {
          this.lectura2 += "4";
          this.pantalla.append("4");
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura2);
          this.remplazador.deleteCharAt(0);
          this.lectura2 = this.remplazador.toString();
          this.lectura2 += "4";
          this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
        }
        break;
      case 53: 
        if (this.señal)
        {
          if (!this.lectura1.equals("0"))
          {
            if (this.escritor)
            {
              this.lectura1 = "5";
              this.pantalla.setText("5");
              this.escritor = false;
            }
            else
            {
              this.lectura1 += "5";
              this.pantalla.append("5");
            }
          }
          else
          {
            this.remplazador = new StringBuilder(this.lectura1);
            this.remplazador.deleteCharAt(0);
            this.lectura1 = this.remplazador.toString();
            this.lectura1 += "5";
            this.pantalla.setText(this.lectura1);
          }
          this.pantalla_resultado.setText("");
        }
        else if (!this.lectura2.equals("0"))
        {
          this.lectura2 += "5";
          this.pantalla.append("5");
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura2);
          this.remplazador.deleteCharAt(0);
          this.lectura2 = this.remplazador.toString();
          this.lectura2 += "5";
          this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
        }
        break;
      case 54: 
        if (this.señal)
        {
          if (!this.lectura1.equals("0"))
          {
            if (this.escritor)
            {
              this.lectura1 = "6";
              this.pantalla.setText("6");
              this.escritor = false;
            }
            else
            {
              this.lectura1 += "6";
              this.pantalla.append("6");
            }
          }
          else
          {
            this.remplazador = new StringBuilder(this.lectura1);
            this.remplazador.deleteCharAt(0);
            this.lectura1 = this.remplazador.toString();
            this.lectura1 += "6";
            this.pantalla.setText(this.lectura1);
          }
          this.pantalla_resultado.setText("");
        }
        else if (!this.lectura2.equals("0"))
        {
          this.lectura2 += "6";
          this.pantalla.append("6");
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura2);
          this.remplazador.deleteCharAt(0);
          this.lectura2 = this.remplazador.toString();
          this.lectura2 += "6";
          this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
        }
        break;
      case 55: 
        if (this.señal)
        {
          if (!this.lectura1.equals("0"))
          {
            if (this.escritor)
            {
              this.lectura1 = "7";
              this.pantalla.setText("7");
              this.escritor = false;
            }
            else
            {
              this.lectura1 += "7";
              this.pantalla.append("7");
            }
          }
          else
          {
            this.remplazador = new StringBuilder(this.lectura1);
            this.remplazador.deleteCharAt(0);
            this.lectura1 = this.remplazador.toString();
            this.lectura1 += "1";
            this.pantalla.setText(this.lectura1);
          }
          this.pantalla_resultado.setText("");
        }
        else if (!this.lectura2.equals("0"))
        {
          this.lectura2 += "7";
          this.pantalla.append("7");
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura2);
          this.remplazador.deleteCharAt(0);
          this.lectura2 = this.remplazador.toString();
          this.lectura2 += "7";
          this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
        }
        break;
      case 56: 
        if (this.señal)
        {
          if (!this.lectura1.equals("0"))
          {
            if (this.escritor)
            {
              this.lectura1 = "8";
              this.pantalla.setText("8");
              this.escritor = false;
            }
            else
            {
              this.lectura1 += "8";
              this.pantalla.append("8");
            }
          }
          else
          {
            this.remplazador = new StringBuilder(this.lectura1);
            this.remplazador.deleteCharAt(0);
            this.lectura1 = this.remplazador.toString();
            this.lectura1 += "8";
            this.pantalla.setText(this.lectura1);
          }
          this.pantalla_resultado.setText("");
        }
        else if (!this.lectura2.equals("0"))
        {
          this.lectura2 += "8";
          this.pantalla.append("8");
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura2);
          this.remplazador.deleteCharAt(0);
          this.lectura2 = this.remplazador.toString();
          this.lectura2 += "8";
          this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
        }
        break;
      case 57: 
        if (this.señal)
        {
          if (!this.lectura1.equals("0"))
          {
            if (this.escritor)
            {
              this.lectura1 = "9";
              this.pantalla.setText("9");
              this.escritor = false;
            }
            else
            {
              this.lectura1 += "9";
              this.pantalla.append("9");
            }
          }
          else
          {
            this.remplazador = new StringBuilder(this.lectura1);
            this.remplazador.deleteCharAt(0);
            this.lectura1 = this.remplazador.toString();
            this.lectura1 += "9";
            this.pantalla.setText(this.lectura1);
          }
          this.pantalla_resultado.setText("");
        }
        else if (!this.lectura2.equals("0"))
        {
          this.lectura2 += "9";
          this.pantalla.append("9");
        }
        else
        {
          this.remplazador = new StringBuilder(this.lectura2);
          this.remplazador.deleteCharAt(0);
          this.lectura2 = this.remplazador.toString();
          this.lectura2 += "9";
          this.pantalla.setText(this.lectura1 + this.operacion + this.lectura2);
        }
        break;
      case 521: 
        if (this.operacion.equals(""))
        {
          if (this.resultadoIsActive)
          {
            this.pantalla_resultado.setText("");
            this.pantalla.setText(this.lectura1);
          }
          this.pantalla.append("+");
          this.operacion = "+";
          this.señal = false;
        }
        else if (!this.lectura1.equals(""))
        {
          this.pantalla.setText(this.lectura1);
          operaResultados();
          if (!this.validador_operaciones)
          {
            this.operacion = "+";
            this.señal = false;
            this.pantalla.append("+");
          }
        }
        break;
      case 45: 
        if (this.operacion.equals(""))
        {
          if (this.resultadoIsActive)
          {
            this.pantalla_resultado.setText("");
            this.pantalla.setText(this.lectura1);
          }
          this.pantalla.append("-");
          this.operacion = "-";
          this.señal = false;
        }
        else if (!this.lectura1.equals(""))
        {
          this.pantalla.setText(this.lectura1);
          operaResultados();
          if (!this.validador_operaciones)
          {
            this.operacion = "-";
            this.señal = false;
            this.pantalla.append("-");
          }
        }
        break;
      case 10: 
        operaResultados();
      }
    }
    requestFocus();
    mensajeMemoria();
  }
  
  public void keyTyped(KeyEvent tecla_tecleada)
  {
    int kt = tecla_tecleada.getKeyCode();
  }
  
  public void keyReleased(KeyEvent tecla_soltada)
  {
    if (tecla_soltada.getKeyCode() == 16) {
      this.shiftIsPressed = false;
    } else if (tecla_soltada.getKeyCode() == 17) {
      this.ctrlIsPressed = false;
    }
  }
  
  public void colorearOperaciones(Color background, Color foreground)
  {
    this.borrar.setBackground(Color.RED);
    this.borrar.setContentAreaFilled(false);
    this.borrar.setOpaque(true);
    this.borrar.setForeground(Color.WHITE);
    
    this.atras.setBackground(Color.RED);
    this.atras.setContentAreaFilled(false);
    this.atras.setOpaque(true);
    this.atras.setForeground(Color.WHITE);
    
    this.negar.setBackground(background);
    this.negar.setContentAreaFilled(false);
    this.negar.setOpaque(true);
    this.negar.setForeground(foreground);
    
    this.mas.setBackground(background);
    this.mas.setContentAreaFilled(false);
    this.mas.setOpaque(true);
    this.mas.setForeground(foreground);
    
    this.menos.setBackground(background);
    this.menos.setContentAreaFilled(false);
    this.menos.setOpaque(true);
    this.menos.setForeground(foreground);
    
    this.por.setBackground(background);
    this.por.setContentAreaFilled(false);
    this.por.setOpaque(true);
    this.por.setForeground(foreground);
    
    this.entre.setBackground(background);
    this.entre.setContentAreaFilled(false);
    this.entre.setOpaque(true);
    this.entre.setForeground(foreground);
    
    this.igual.setBackground(Color.ORANGE);
    this.igual.setContentAreaFilled(false);
    this.igual.setOpaque(true);
    this.igual.setForeground(Color.BLACK);
  }
  
  public void colorearNumeros(Color background, Color foreground)
  {
    this.punto.setBackground(background);
    this.punto.setContentAreaFilled(false);
    this.punto.setOpaque(true);
    this.punto.setForeground(foreground);
    
    this.cero.setBackground(background);
    this.cero.setContentAreaFilled(false);
    this.cero.setOpaque(true);
    this.cero.setForeground(foreground);
    
    this.uno.setBackground(background);
    this.uno.setContentAreaFilled(false);
    this.uno.setOpaque(true);
    this.uno.setForeground(foreground);
    
    this.dos.setBackground(background);
    this.dos.setContentAreaFilled(false);
    this.dos.setOpaque(true);
    this.dos.setForeground(foreground);
    
    this.tres.setBackground(background);
    this.tres.setContentAreaFilled(false);
    this.tres.setOpaque(true);
    this.tres.setForeground(foreground);
    
    this.cuatro.setBackground(background);
    this.cuatro.setContentAreaFilled(false);
    this.cuatro.setOpaque(true);
    this.cuatro.setForeground(foreground);
    
    this.cinco.setBackground(background);
    this.cinco.setContentAreaFilled(false);
    this.cinco.setOpaque(true);
    this.cinco.setForeground(foreground);
    
    this.seis.setBackground(background);
    this.seis.setContentAreaFilled(false);
    this.seis.setOpaque(true);
    this.seis.setForeground(foreground);
    
    this.siete.setBackground(background);
    this.siete.setContentAreaFilled(false);
    this.siete.setOpaque(true);
    this.siete.setForeground(foreground);
    
    this.ocho.setBackground(background);
    this.ocho.setContentAreaFilled(false);
    this.ocho.setOpaque(true);
    this.ocho.setForeground(foreground);
    
    this.nueve.setBackground(background);
    this.nueve.setContentAreaFilled(false);
    this.nueve.setOpaque(true);
    this.nueve.setForeground(foreground);
    
    this.raiz.setBackground(Color.YELLOW);
    this.raiz.setContentAreaFilled(false);
    this.raiz.setOpaque(true);
  }
  
  public void colorearMemoria(Color background, Color foreground)
  {
    this.limpiar_memoria.setBackground(background);
    this.limpiar_memoria.setContentAreaFilled(false);
    this.limpiar_memoria.setOpaque(true);
    this.limpiar_memoria.setForeground(foreground);
    
    this.mostrar_memoria.setBackground(background);
    this.mostrar_memoria.setContentAreaFilled(false);
    this.mostrar_memoria.setOpaque(true);
    this.mostrar_memoria.setForeground(foreground);
    
    this.establecer_memoria.setBackground(background);
    this.establecer_memoria.setContentAreaFilled(false);
    this.establecer_memoria.setOpaque(true);
    this.establecer_memoria.setForeground(foreground);
    
    this.sumar_memoria.setBackground(background);
    this.sumar_memoria.setContentAreaFilled(false);
    this.sumar_memoria.setOpaque(true);
    this.sumar_memoria.setForeground(foreground);
    
    this.restar_memoria.setBackground(background);
    this.restar_memoria.setContentAreaFilled(false);
    this.restar_memoria.setOpaque(true);
    this.restar_memoria.setForeground(foreground);
  }
  
  public static void main(String[] args)
  {
    Ventana calculadora = new Ventana();
    calculadora.colorearOperaciones(Color.MAGENTA, Color.BLACK);
    calculadora.colorearNumeros(Color.GREEN, Color.BLACK);
    calculadora.colorearMemoria(Color.BLUE, Color.WHITE);
    calculadora.setFocusable(true);
    calculadora.requestFocus();
  }
}
