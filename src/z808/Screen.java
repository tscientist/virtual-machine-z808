package z808;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

public class Screen extends javax.swing.JFrame {
    DefaultTableModel model;
    public Screen(Z808 proc) {
        super("Z808");
        initComponents();
        this.proc = proc;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Painel = new javax.swing.JPanel();
        AbrirBotao = new javax.swing.JButton();
        CarregarBotao = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        MemTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        DSVALOR = new javax.swing.JLabel();
        IPValor = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        DXVALOR = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        SIValor = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        SPValor = new javax.swing.JLabel();
        ExecutarBotao = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        DSValor = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        SSValor = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        LastInst = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        CSValor = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        SRValor = new javax.swing.JLabel();
        SPVALOR = new javax.swing.JLabel();
        ultimaInst = new javax.swing.JTextField();
        IPVALOR = new javax.swing.JLabel();
        AXVALOR = new javax.swing.JLabel();
        SRVALOR = new javax.swing.JLabel();
        CSVALOR = new javax.swing.JLabel();
        SIVALOR = new javax.swing.JLabel();
        SSVALOR = new javax.swing.JLabel();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        AbrirBotao.setText("Open");
        AbrirBotao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirBotaoActionPerformed(evt);
            }
        });

        CarregarBotao.setText("Load File");
        CarregarBotao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CarregarBotaoActionPerformed(evt);
            }
        });

        MemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Address", "Instruction"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(MemTable);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setText("IP:");

        DSVALOR.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N

        IPValor.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel2.setText("AX:");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel3.setText("DX:");

        DXVALOR.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel4.setText("SI:");

        SIValor.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel5.setText("SP:");

        SPValor.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N

        ExecutarBotao.setText("RUN");
        ExecutarBotao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExecutarBotaoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel7.setText("DS:");

        DSValor.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel8.setText("SS:");

        SSValor.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N

        jLabel9.setText("Last load instruction:");

        LastInst.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel10.setText("CS:");

        CSValor.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel11.setText("SR:");

        SRValor.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N

        SPVALOR.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N

        IPVALOR.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N

        AXVALOR.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N

        SRVALOR.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N

        CSVALOR.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N

        SIVALOR.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N

        SSVALOR.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N


        javax.swing.GroupLayout PainelLayout = new javax.swing.GroupLayout(Painel);
        Painel.setLayout(PainelLayout);
        PainelLayout.setHorizontalGroup(
            PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelLayout.createSequentialGroup()
                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PainelLayout.createSequentialGroup()
                                .addComponent(AbrirBotao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CarregarBotao))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel9)
                            .addComponent(ultimaInst, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PainelLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(LastInst, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(PainelLayout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PainelLayout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(IPValor)
                                            .addGroup(PainelLayout.createSequentialGroup()
                                                .addComponent(SIValor)
                                                .addGap(49, 49, 49)
                                                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(SPValor)
                                                    .addComponent(DSValor)
                                                    .addComponent(SSValor)
                                                    .addComponent(CSValor))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(SRValor, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(ExecutarBotao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(PainelLayout.createSequentialGroup()
                                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(PainelLayout.createSequentialGroup()
                                                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel1)
                                                    .addComponent(jLabel2))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(PainelLayout.createSequentialGroup()
                                                        .addComponent(AXVALOR, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(37, 37, 37)
                                                        .addComponent(jLabel11))
                                                    .addComponent(IPVALOR, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jLabel4)
                                            .addGroup(PainelLayout.createSequentialGroup()
                                                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(PainelLayout.createSequentialGroup()
                                                        .addComponent(jLabel3)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(DXVALOR, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(PainelLayout.createSequentialGroup()
                                                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel7)
                                                            .addComponent(jLabel8))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(DSVALOR, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(SSVALOR, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(PainelLayout.createSequentialGroup()
                                                        .addGap(38, 38, 38)
                                                        .addComponent(jLabel5))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelLayout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel10)))))
                                        .addGap(53, 53, 53)
                                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(SPVALOR, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CSVALOR, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(SRVALOR, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(SIVALOR, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())))))
        );
        PainelLayout.setVerticalGroup(
            PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelLayout.createSequentialGroup()
                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AbrirBotao)
                            .addComponent(CarregarBotao)
                            .addComponent(IPValor)))
                    .addGroup(PainelLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(ExecutarBotao)))
                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelLayout.createSequentialGroup()
                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PainelLayout.createSequentialGroup()
                                .addComponent(SRValor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SRVALOR, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PainelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(PainelLayout.createSequentialGroup()
                                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel1)
                                            .addComponent(IPVALOR, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2)
                                            .addComponent(AXVALOR, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(PainelLayout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(4, 4, 4)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(DXVALOR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(SPVALOR, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PainelLayout.createSequentialGroup()
                                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(DSVALOR, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(SSVALOR, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PainelLayout.createSequentialGroup()
                                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(PainelLayout.createSequentialGroup()
                                        .addComponent(SIVALOR, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CSVALOR, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PainelLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(11, 11, 11)
                                        .addComponent(jLabel10)))
                                .addGap(2, 2, 2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SIValor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SPValor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DSValor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SSValor)
                        .addGap(0, 0, 0)
                        .addComponent(CSValor)
                        .addGap(77, 77, 77)
                        .addComponent(LastInst))
                    .addGroup(PainelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ultimaInst, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 11, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Painel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void ExecutarBotaoActionPerformed(java.awt.event.ActionEvent evt) {
        String opcode;
        //do{
            if((flag_para == 0) && !(IPVALOR.getText().isEmpty())){
                int indice_linha = MemTable.getSelectedRow();

                int end_real = proc.getIP();
                try{
                    Instruction inst = (Instruction) proc.getMemory().get(end_real);
                    opcode = inst.getOpcode();
                    int end_alterado = proc.run(inst, ultimaInst, end_real);
                    atualiza_regsandflags();
                    if( end_alterado != -1){      //precisa atualizar a tabela de memoria
                        int linha = proc.getIndex().indexOf(end_alterado);
                        MemTable.setValueAt(proc.getMemory().get(end_alterado).getValue(), linha, 1);
                    }

                }
                catch(ClassCastException e){
                    opcode = "Dado";
                }

                if (opcode.equals("EE")){
                    flag_para = 1;
                }
                else{
                    int prox_linha = proc.getIndex().indexOf(Integer.parseInt(IPVALOR.getText()));
                    MemTable.setRowSelectionInterval(prox_linha, prox_linha);
                    flag_para = 0;
                }
            }
    }//GEN-LAST:event_ExecutarBotaoActionPerformed

    private void CarregarBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CarregarBotaoActionPerformed
        if (arqv != null) {
            proc.loadFirstData();
            int tam_extra = 0;
            proc.setSP(proc.loadStack(tam_extra));
            SPVALOR.setText(String.valueOf(proc.getSP()));  //atualiza valor do reg sp com a pos do final da pilha
            this.model=new DefaultTableModel(){   //cria um default table model
                @Override
                public boolean isCellEditable(int rowIndex, int mColIndex) {  //torna a tabela nao editavel para o usuario
                    return false;
                }
            } ;
            MemTable.setModel(model);   //atrela o modelo criado a jtable MemTable
            model.addColumn("Address");    //adiciona coluna de endereço
            model.addColumn("Instruction");  //adiciona coluna de instrucao/dados
            MemTable.getColumnModel().getColumn(0).setPreferredWidth(30);  //seta largura da coluna 0
            MemTable.getColumnModel().getColumn(1).setPreferredWidth(110);  //seta largura da coluna 1

            ArrayList<Integer> Indices = proc.getIndex();
            Hashtable<Integer,Key> Memoria = proc.getMemory();
            for(int i=0; i < Indices.size(); i++){
                int end = Indices.get(i);
                Key algo = Memoria.get(end);
                try{
                    Value d = (Value)algo;
                    model.addRow(new String[]{String.valueOf(end),String.valueOf(d.getValue())});
                }
                catch(ClassCastException e){
                    Instruction inst = (Instruction) algo;
                    String opcode = inst.getOpcode();
                    if(inst.getType() == 1){
                        model.addRow(new String[]{String.valueOf(end),opcode});
                    }
                    else{
                        model.addRow(new String[]{String.valueOf(end),opcode+" "+inst.getValue()});
                    }
                }
            }
            // model.
            MemTable.setRowSelectionInterval(proc.getQtde_dados1(), proc.getQtde_dados1());
            proc.setIP(Indices.get(MemTable.getSelectedRow()));
            IPVALOR.setText(String.valueOf(proc.getIP()));
        }
    }

    private void AbrirBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirBotaoActionPerformed
        jFileChooser1 = new JFileChooser("/Users/lgcantarelli/Projects/z808-maquina");   //cria o jfilechooser
        jFileChooser1.setAcceptAllFileFilterUsed(false);  //nao deixa ele mostrar all files
        jFileChooser1.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);  //mostrar apenas diretorios e arquivos
        jFileChooser1.addChoosableFileFilter(new MyFilter());    //atrela ao filtro myfilter (pra mostrar apenas .asm e diretorios)
        int returnval = jFileChooser1.showOpenDialog(null); //mostra a janela para abrir arquivos
        if( (returnval == JFileChooser.CANCEL_OPTION) || returnval == JFileChooser.ERROR_OPTION){
            arqv = null;
        }
        else{
            arqv = jFileChooser1.getSelectedFile();
            proc.setLig(arqv);
        }
        
    }

    private javax.swing.JLabel AXVALOR;
    private javax.swing.JButton AbrirBotao;
    private javax.swing.JLabel CSVALOR;
    private javax.swing.JLabel CSValor;
    private javax.swing.JButton CarregarBotao;
    private javax.swing.JLabel DSVALOR;
    private javax.swing.JLabel DSValor;
    private javax.swing.JLabel DXVALOR;
    private javax.swing.JButton ExecutarBotao;
    private javax.swing.JLabel IPVALOR;
    private javax.swing.JLabel IPValor;
    private javax.swing.JLabel LastInst;
    private javax.swing.JTable MemTable;
    private javax.swing.JCheckBox Overflowflag;
    private javax.swing.JPanel Painel;
    private javax.swing.JLabel SIVALOR;
    private javax.swing.JLabel SIValor;
    private javax.swing.JLabel SPVALOR;
    private javax.swing.JLabel SPValor;
    private javax.swing.JLabel SRVALOR;
    private javax.swing.JLabel SRValor;
    private javax.swing.JLabel SSVALOR;
    private javax.swing.JLabel SSValor;
    private javax.swing.JCheckBox Sinalflag;
    private javax.swing.JCheckBox Zeroflag;
    private javax.swing.JComboBox comboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField ultimaInst;

    private JFileChooser jFileChooser1;
    private File arqv = null;
    private int flag_para = 0;
    private Z808 proc;

    private void atualiza_regsandflags() {
        AXVALOR.setText(String.valueOf(proc.getAX()));
        IPVALOR.setText(String.valueOf(proc.getIP()));
        DXVALOR.setText(String.valueOf(proc.getDX()));
        SIVALOR.setText(String.valueOf(proc.getSI()));
        SPVALOR.setText(String.valueOf(proc.getSP()));
        SSVALOR.setText(String.valueOf(proc.getSS()));
        DSVALOR.setText(String.valueOf(proc.getDS()));
        CSVALOR.setText(String.valueOf(proc.getCS()));
        SRVALOR.setText(String.valueOf(proc.getSR()));
    }
    
    private static class MyFilter extends FileFilter {

        public MyFilter() {
        }
        
        @Override
        public boolean accept(File f) {
           String filename = f.getName();
           return filename.endsWith(".txt") || f.isDirectory();
        }

        @Override
        public String getDescription() {
           return "*.txt";
        }
    }

}
