/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package framePackage;

import classPackage.ekstraClass;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ASUS
 */
public class statistikPanel extends javax.swing.JPanel {

    /**
     * Creates new form statistikPanel
     */
    public statistikPanel() {
        initComponents();
        loadData();
        tampilDiagram();
    }

    void loadData() {
        ekstraClass dataJumlah = new ekstraClass();
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Nama Ekstrakurikuler");
        model.addColumn("Jumlah Siswa");

        try {
            ResultSet rsVar = dataJumlah.showStatiska();

            while (rsVar.next()) {
                String namaEkstra = rsVar.getString("nama_ekstra");
                String jumlahSiswa = rsVar.getString("jumlah_siswa");

                Object[] data = {namaEkstra, jumlahSiswa};
                model.addRow(data);
            }
            tableStatistik.setModel(model);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
    }

    void tampilDiagram() {
        ekstraClass dataJumlah = new ekstraClass();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        ResultSet rsVar = dataJumlah.showStatiska();
        try {

            while (rsVar.next()) {
                String namaEkstra = rsVar.getString("nama_ekstra");
                int jumlahSiswa = rsVar.getInt("jumlah_siswa");

                // menambah data ke dataset grafik
                dataset.addValue(jumlahSiswa, "Jumlah Siswa", namaEkstra);
            }
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }

        // membuat chart batang dengan posisi horizontal dan tanpa judul
        JFreeChart chart = ChartFactory.createBarChart("", "", "", dataset, PlotOrientation.HORIZONTAL, false, true, false);

        // mengatur warna
        Color biruTua = new Color(30, 90, 170);
        Color biruMuda = new Color(235, 245, 255);

        // mengambil objek area utama grafik
        CategoryPlot plot = chart.getCategoryPlot();
        
        // mengatur font untuk label sumbu kategori dan sumbu nilai (jumlah siswa)
        plot.getDomainAxis().setTickLabelFont(new java.awt.Font("Arial", Font.NORMAL, 12));
        plot.getRangeAxis().setTickLabelFont(new java.awt.Font("Arial", Font.NORMAL, 12));
        
        // memberi warna latar belakang dan garis grid pada grafik
        plot.setBackgroundPaint(biruMuda);
        plot.setRangeGridlinePaint(biruTua);

        // mengambil renderer untuk mengatur tampilan bar
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        
        // mengatur warna grafik batang untuk batang seri 0 
        renderer.setSeriesPaint(0, biruTua);
        
        // mengatur agar grafik batang dibuat dengan standar
        renderer.setBarPainter(new StandardBarPainter());

        // membuat panel JFreeChart yang akan ditempatkan di GUI
        ChartPanel chartPanel = new ChartPanel(chart);
        
        // menyesuaikan ukuran chartPanel dengan ukuran JPanel panelDiagram
        chartPanel.setPreferredSize(new Dimension(panelDiagram.getWidth(), panelDiagram.getHeight()));
        
        // menampilkan chart ke JPanel panelDiagram
        panelDiagram.removeAll();
        panelDiagram.setLayout(new BorderLayout());
        panelDiagram.add(chartPanel);  // menambahkan chart ke JPanel panelDiagram
        panelDiagram.validate();
        panelDiagram.repaint();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableStatistik = new javax.swing.JTable();
        buttonSimpanData = new javax.swing.JButton();
        panelDiagram = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(30, 58, 138));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("DATA STATISTIK EKSTRAKURIKULER");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel8)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("DATA STATISTIK EKSTRAKURIKULER");

        tableStatistik.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableStatistik.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableStatistik);

        buttonSimpanData.setBackground(new java.awt.Color(40, 167, 69));
        buttonSimpanData.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonSimpanData.setForeground(new java.awt.Color(255, 255, 255));
        buttonSimpanData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesPackage/icons8-save-as-24.png"))); // NOI18N
        buttonSimpanData.setText("SIMPAN DATA");
        buttonSimpanData.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonSimpanData.setIconTextGap(8);
        buttonSimpanData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSimpanDataActionPerformed(evt);
            }
        });

        panelDiagram.setBackground(new java.awt.Color(204, 204, 204));
        panelDiagram.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSimpanData, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(panelDiagram, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                    .addComponent(panelDiagram, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addComponent(buttonSimpanData, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSimpanDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSimpanDataActionPerformed
        // TODO add your handling code here:
        
        // membuat file chooser dan memberi judul dialog
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Menyimpan Data Statistik Ekstrakurikuler");

        // menampilkan dialog penyimpanan dan menunggu interaksi pengguna
        int userSelection = chooser.showSaveDialog(this);

        // pengkondisian jika pengguna memilih tombol save
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            
            // mengambil objek file yang dipilih sebagai lokasi penyimpanan
            File fileToSave = chooser.getSelectedFile();

            try {
                // membuat objek document sebagai representasi dokumen pdf
                Document document = new Document();
                
                // menghubungkan document dengan output stream file pdf menggunakan pdfwriter
                com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream(fileToSave.getAbsoluteFile() + ".pdf"));
                
                // membuka dokumen sehingga dapat menerima content
                document.open();

                // mendefinisikan dan mengatur font untuk header dan isi cell tabel
                Font fontHeader = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
                Font fontCell = new Font(Font.FontFamily.HELVETICA, 11);

                // menyusun judul dokumen
                Paragraph title = new Paragraph("Tabel Statistik Ekstrakurikuler".toUpperCase(), new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD));

                // membuat paragraf untuk informasi tanggal
                Paragraph tanggal = new Paragraph("Data diakses pada :  " + mainFrame.labelWaktu.getText(), new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL));

                // mengatur judul agar berada di tengah
                title.setAlignment(Element.ALIGN_CENTER);
                
                // menambahkan komponen kedalam dokumen
                document.add(title);
                document.add(new Paragraph(" "));
                document.add(new Paragraph(" "));
                document.add(new Paragraph(tanggal));

                // membuat objek tabel pdf dengan jumlah kolom sesuai dengan JTable
                PdfPTable pdfTable = new PdfPTable(tableStatistik.getColumnCount());
                
                // menetapkan agar tabel mengisi seluruh lebar halaman pdf
                pdfTable.setWidthPercentage(100);
                
                // memberi jarak sebelum dan setelah tabel agar tidak menempel dengan elemen berikutnya
                pdfTable.setSpacingBefore(10f);
                pdfTable.setSpacingAfter(10f);

                for (int i = 0; i < tableStatistik.getColumnCount(); i++) {
                    // mengambil nama kolom dari JTable dan memasukkannya kedalam cell pdf
                    PdfPCell cell = new PdfPCell(new Phrase(tableStatistik.getColumnName(i), fontHeader));
                    
                    // menyusun text agar berada di tengah
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    
                    // mengatur warna latar belakang abu-abu untuk membedakan header
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    
                    // menambahkan ruang atas dan bawah pada text
                    cell.setPaddingTop(5);
                    cell.setPaddingBottom(5);
                    
                    // memasukkan cell kedalam tabel pdf
                    pdfTable.addCell(cell);
                }

                for (int row = 0; row < tableStatistik.getRowCount(); row++) {
                    for (int col = 0; col < tableStatistik.getColumnCount(); col++) {

                        // mengambil nalai cell JTable, jika null dikonversi menjadi string kosong
                        Object value = tableStatistik.getValueAt(row, col);
                        
                        // membuat cell pdf dengan isi sesuai data table
                        PdfPCell cell = new PdfPCell(new Phrase(value == null ? "" : value.toString(), fontCell));
                        
                        // mengatur text agar rata kiri
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        
                        // menambahkan alignment vertikal agar text berada ditengah secara vertikal
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        
                        // memberikan padding
                        cell.setPaddingLeft(6);
                        cell.setPaddingTop(6);
                        cell.setPaddingRight(6);
                        cell.setPaddingBottom(6);
                        
                        // menambahkan cell ke table pdf
                        pdfTable.addCell(cell);
                    }
                }

                // masukkan tabel yang telah dibangun kedalam dokumen pdf
                document.add(pdfTable);
                
                // membari jarak sebelum content berikutnya
                document.add(new Paragraph(""));
                
                // menambahkan halaman baru (untuk content yang baru) 
                document.newPage();
                
                // membuat buffer image dengan ukuran panel grafik untuk menangkap tampilan
                BufferedImage img = new BufferedImage(panelDiagram.getWidth(), panelDiagram.getHeight(), BufferedImage.TYPE_INT_RGB);
                
                // mendapatkan objek Graphics2D untuk menggambar panel
                Graphics2D g2 = img.createGraphics();
                
                // melakukan rendering panel diagram kedalam objek gambar
                panelDiagram.paint(g2);
                
                // menghapus objek graphics untuk mengurangi penggunaan memori
                g2.dispose();
                
                // membuat output stream sementara untuk menampung gambar sebelum dikonversi ke pdf
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                
                // menulis gambar dalam format png
                ImageIO.write(img, "png", stream);
                
                // mengkonversi byte array dari stream menjadi objek image untuk pdf
                Image pdfImage = Image.getInstance(stream.toByteArray());
                
                // menyesuaikan ukuran gambar dengan halaman pdf
                pdfImage.scaleToFit(550, 325);
                
                // menempatkan gambar ditengah halaman pdf
                pdfImage.setAlignment(Element.ALIGN_CENTER);                
                
                // menambahkan judul grafik
                Paragraph titleGrafik = new Paragraph("Grafik Statistik Ekstrakurikuler".toUpperCase(), new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD));
                
                // menambahkan judul dan tanggal grafik ke pdf
                titleGrafik.setAlignment(Element.ALIGN_CENTER);
                document.add(titleGrafik);
                document.add(new Paragraph(" "));
                document.add(new Paragraph(" "));
                document.add(new Paragraph(tanggal));
                document.add(new Paragraph(" "));
                
                // menambahkan gambar grafik ke pdf
                document.add(pdfImage);                
                
                // menutup dokumen setelah semua content selesai ditambahkan
                document.close();

                JOptionPane.showMessageDialog(this, "Data berhasil disimpan ke PDF:\n" + fileToSave.getAbsolutePath() + ".pdf");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan PDF : " + e.getMessage());
            }
        }
    }//GEN-LAST:event_buttonSimpanDataActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSimpanData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelDiagram;
    private javax.swing.JTable tableStatistik;
    // End of variables declaration//GEN-END:variables
}
