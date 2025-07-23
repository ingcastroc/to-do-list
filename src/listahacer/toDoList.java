
package listahacer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author sonia
 */
public class toDoList extends javax.swing.JFrame {
    public static MinHeap arbol = new MinHeap(100);
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(toDoList.class.getName());

    private DefaultListModel<String> modeloLista;
    public listaEnlazada lista;
   private boolean mostrandoDesdeHeap = false;
    private static boolean fechaYaIdentificada = false;
    public toDoList() {
        initComponents();
        PersistenciaHeap.reconstruirHeapDesdeArchivos(arbol);
        desTarea.setLineWrap(true);         
        desTarea.setWrapStyleWord(true);
        this.setLocation(200, 100);
        desTarea.setEnabled(false);
        lista = new listaEnlazada();
        if (!fechaYaIdentificada) {
    String d = identificarFecha();
        if(!d.equals("el archivo solicitado no existe")){
            rellenarLista h = new rellenarLista();
            this.lista = h.lector(d);
           
             } else {
            this.lista = new listaEnlazada(); 
             }
                    fechaYaIdentificada = true;
        }
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        jDateChooser1.setCalendar(c);
        jDateChooser1.setMinSelectableDate(c.getTime()); 
        jDateChooser1.getDateEditor().setEnabled(false);
        Date fecha = jDateChooser1.getDate();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MMMM-yyyy");
        String fechasimple = formato.format(fecha);
        modeloLista = new DefaultListModel<String>();
        Lista.setModel(modeloLista);
        
        ActualizarArchivosData();
        actualizarList();
        LocalDate hoy = LocalDate.now();
        Lista.addListSelectionListener(evt -> {
    if (!evt.getValueIsAdjusting()) {
        String seleccionado = Lista.getSelectedValue();

        if (mostrandoDesdeHeap) {
            Date fechaSeleccionada = jDateChooser1.getDate();
            LocalDate fechaBusqueda = fechaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
             ArrayList<TareaConFecha> tareasEncontradas = arbol.buscarPorFecha(fechaBusqueda);
            for (TareaConFecha tarea : tareasEncontradas) {
                if (tarea.titulo.equals(seleccionado)) {
                    desTarea.setText(tarea.descripcion);
                    break;
                }
            }
        } else {
            // Buscar en la lista enlazada
            String descriptor = lista.obtenerDescripcionPorTitulo(seleccionado);
            desTarea.setText(descriptor);
        }
    }
});
        
        jCheckBox1.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (jCheckBox1.isSelected()) {
            String seleccionado = Lista.getSelectedValue();
            lista.eliminarTarea(seleccionado);
            actualizarList();
            EliminarTareaTXT.eliminarPorTitulo(seleccionado);
      
            jCheckBox1.setSelected(false);
        } else {
            System.out.println("El checkbox fue deseleccionado");
        }
    }
});
        
    }
    public void actualizarList(){
            modeloLista.clear();
            for (String tarea : lista.obtenerTareasComoTexto()) {
            modeloLista.addElement(tarea);
                
            }
            Lista.setModel(modeloLista);
    }
    public String identificarFecha(){
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MMMM-yyyy", new Locale("es", "ES"));
        String fechaconformato = hoy.format(formato)+".txt";
        
        File archivo = new File(fechaconformato);
        if(archivo.exists()){
            
            return fechaconformato;
        }else{
            
            return "el archivo solicitado no existe";
        }
    } 
    public void EliminarTodo(String target){
        File archivo = new File(target);

        if (archivo.exists()) {
            if (archivo.delete()) {
                System.out.println("Archivo eliminado correctamente.");
            } else {
                System.out.println(" No se pudo eliminar el archivo.");
            }
        } else {
            System.out.println("️ El archivo no existe.");
        }
        
    }
    public void ActualizarArchivosData(){
        File directorio = new File(".");
        File[] archivos = directorio.listFiles((dir, name) -> name.endsWith(".txt"));

        if (archivos == null) return;

        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MMMM-yyyy", new Locale("es"));

        for (File archivo : archivos) {
            String nombre = archivo.getName().replace(".txt", "");

            // Puede tener sufijo _heap
            if (nombre.endsWith("_heap")) {
                nombre = nombre.replace("_heap", "");
            }

            try {
                LocalDate fechaArchivo = LocalDate.parse(nombre, formatoFecha);
                if (fechaArchivo.isBefore(hoy)) {
                    if (archivo.delete()) {
                        System.out.println("🗑️ Archivo eliminado: " + archivo.getName());
                    } else {
                        System.err.println("⚠️ No se pudo eliminar: " + archivo.getName());
                    }
                }
            } catch (Exception e) {
                
            }
        }
    
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Lista = new javax.swing.JList<>();
        label1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        desTarea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Lista.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ListaValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(Lista);

        label1.setText("Tareas según la fecha");

        jCheckBox1.setText("completa");

        jButton1.setText("Agregar ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        desTarea.setColumns(20);
        desTarea.setRows(5);
        jScrollPane2.setViewportView(desTarea);

        jLabel1.setText("Descripcion");

        jLabel2.setText("consultar tareas por fecha");

        jButton2.setText("Actualizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Consultar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(label1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jCheckBox1))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(label1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(128, 128, 128))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        agregarTarea ejecutar = new agregarTarea(this);
        ejecutar.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ListaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ListaValueChanged
        
    }//GEN-LAST:event_ListaValueChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        EliminarTodo(identificarFecha());
        for (String tareas : lista.obtenerTareasComoTexto()) {
            String descripcion=lista.obtenerDescripcionPorTitulo(tareas);
            persistenciaDatos.crearArchivoTarea(tareas,descripcion);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Date fechaSeleccionada = jDateChooser1.getDate(); 
        LocalDate fechaBusqueda = fechaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate hoy = LocalDate.now();
        if(fechaBusqueda.isEqual(hoy)){
            actualizarList();
        }else{
        ArrayList<TareaConFecha> tareasEncontradas = arbol.buscarPorFecha(fechaBusqueda);
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (TareaConFecha tarea : tareasEncontradas) {
             modelo.addElement(tarea.titulo);
        }
        Lista.setModel(modelo);
        }    
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new toDoList().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> Lista;
    private javax.swing.JTextArea desTarea;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label1;
    // End of variables declaration//GEN-END:variables
}
