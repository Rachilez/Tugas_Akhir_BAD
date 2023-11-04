package Form;

import Config.Connect;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowFilter;
import javax.swing.SortOrder;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;

public class Library extends javax.swing.JFrame {

    public Library() {
        initComponents();
        Connection con = Connect.getKoneksi();
        Tables();
    }

    public void Tables() {
        try {

            Connection connection = Connect.getKoneksi();
            String query = "SELECT * FROM perpus";

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getString("No");
                row[1] = rs.getString("Judul");
                row[2] = rs.getString("Penulis");
                row[3] = rs.getString("ISBN");
                row[4] = rs.getString("Genre");

                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                model.addRow(row);
                jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
                jTable1.getColumnModel().getColumn(1).setPreferredWidth(20);
                jTable1.getColumnModel().getColumn(2).setPreferredWidth(20);
                jTable1.getColumnModel().getColumn(3).setPreferredWidth(20);
                jTable1.getColumnModel().getColumn(4).setPreferredWidth(20);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void cariBuku(String keyword) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(rowSorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        int columnIndexToSearch = 0; // Ganti dengan indeks kolom yang ingin Anda cari (0, 1, 2, 3)
        sortKeys.add(new RowSorter.SortKey(columnIndexToSearch, SortOrder.ASCENDING));
        rowSorter.setSortKeys(sortKeys);
        rowSorter.sort();

        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
    }

    private void perbaruiBuku(String no, String judul, String penulis, String isbn, String genre) {
        try {
            Connection connection = Connect.getKoneksi();
            String query = "UPDATE perpus SET judul = ?, penulis = ?,isbn=?, genre = ? WHERE no = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(5, no);
            pstmt.setString(1, judul);
            pstmt.setString(2, penulis);
            pstmt.setString(3, isbn);
            pstmt.setString(4, genre);

            int rowInserted = pstmt.executeUpdate();
            if (rowInserted > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil Di Update");
                resetform();
                Tables();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void hapusBuku(String no) {
        try {
            Connection connection = Connect.getKoneksi();
            String query = "DELETE FROM perpus WHERE no = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, no);
            int rowDelete = pstmt.executeUpdate();
            if (rowDelete > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
                resetform();
                Tables();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void insertBuku(String judul, String penulis, String isbn, String genre) {
        try {
            Connection connection = Connect.getKoneksi();
            String query = "INSERT INTO perpus (judul, penulis, isbn, genre) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, judul);
            pstmt.setString(2, penulis);
            pstmt.setString(3, isbn);
            pstmt.setString(4, genre);

            int rowInserted = pstmt.executeUpdate();
            if (rowInserted > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil Ditambahkan");
                resetform();
                 Tables();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Textbox_Judul = new javax.swing.JTextField();
        Textbox_Penulis = new javax.swing.JTextField();
        Textbox_ISBN = new javax.swing.JTextField();
        Textbox_Genre = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        BT_Batal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        TB_Cari = new javax.swing.JTextField();
        BT_Perbarui = new javax.swing.JButton();
        BT_Hapus = new javax.swing.JButton();
        BT_cari = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Manajemen Buku");

        jLabel2.setText("Judul ");

        jLabel3.setText("Penulis");

        jLabel4.setText("ISBN");

        jLabel5.setText("Genre");

        Textbox_Judul.setToolTipText("Buku");
        Textbox_Judul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Textbox_JudulActionPerformed(evt);
            }
        });

        Textbox_Genre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Textbox_GenreActionPerformed(evt);
            }
        });

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        BT_Batal.setText("Batal");
        BT_Batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_BatalActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No Input", "Judul", "Penulis", "ISBN", "Genre"
            }
        ));
        jTable1.setIntercellSpacing(new java.awt.Dimension(5, 5));
        jTable1.setRowHeight(25);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel6.setText("Cari Buku");

        TB_Cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TB_CariActionPerformed(evt);
            }
        });

        BT_Perbarui.setText("Perbarui");
        BT_Perbarui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_PerbaruiActionPerformed(evt);
            }
        });

        BT_Hapus.setText("Hapus");
        BT_Hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_HapusActionPerformed(evt);
            }
        });

        BT_cari.setText("Cari");
        BT_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_cariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Textbox_Genre)
                            .addComponent(Textbox_ISBN)
                            .addComponent(Textbox_Penulis)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BT_Batal))
                            .addComponent(Textbox_Judul, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(TB_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BT_cari)
                        .addGap(100, 100, 100)
                        .addComponent(BT_Perbarui)
                        .addGap(18, 18, 18)
                        .addComponent(BT_Hapus))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TB_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BT_cari)
                    .addComponent(BT_Perbarui)
                    .addComponent(BT_Hapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(Textbox_Judul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Textbox_Penulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Textbox_ISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(Textbox_Genre, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(BT_Batal)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jButton3.setText("Manajemen Buku");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Data Anggota");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Peminjaman Buku");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String judul = Textbox_Judul.getText();
        String penulis = Textbox_Penulis.getText();
        String isbn = Textbox_ISBN.getText();
        String genre = Textbox_Genre.getText();

        insertBuku(judul, penulis, isbn, genre);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TB_CariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TB_CariActionPerformed

    }//GEN-LAST:event_TB_CariActionPerformed

    private void BT_PerbaruiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_PerbaruiActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin diperbarui.");
        } else {
            String no = jTable1.getValueAt(selectedRow, 0).toString();
            String judul = Textbox_Judul.getText();
            String penulis = Textbox_Penulis.getText();
            String isbn = Textbox_ISBN.getText();
            String genre = Textbox_Genre.getText();
            perbaruiBuku(no, judul, penulis, isbn, genre);

        }
    }//GEN-LAST:event_BT_PerbaruiActionPerformed

    private void BT_HapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_HapusActionPerformed

        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus.");
        } else {
            String no = jTable1.getValueAt(selectedRow, 0).toString();
            hapusBuku(no);
        }
    }//GEN-LAST:event_BT_HapusActionPerformed

    private void BT_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_cariActionPerformed
        String keyword = TB_Cari.getText();
        cariBuku(keyword);
    }//GEN-LAST:event_BT_cariActionPerformed

    private void BT_BatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_BatalActionPerformed
        resetform();
    }//GEN-LAST:event_BT_BatalActionPerformed

    private void Textbox_JudulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Textbox_JudulActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Textbox_JudulActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        Textbox_Judul.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
        Textbox_Penulis.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
        Textbox_ISBN.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
        Textbox_Genre.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void Textbox_GenreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Textbox_GenreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Textbox_GenreActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new Anggota().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new Library().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       new Pengembalian().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Library().setVisible(true);
            }
        });
    }

    private void resetform() {
        Textbox_Judul.setText(null);
        Textbox_Penulis.setText(null);
        Textbox_ISBN.setText(null);
        Textbox_Genre.setText(null);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BT_Batal;
    private javax.swing.JButton BT_Hapus;
    private javax.swing.JButton BT_Perbarui;
    private javax.swing.JButton BT_cari;
    private javax.swing.JTextField TB_Cari;
    private javax.swing.JTextField Textbox_Genre;
    private javax.swing.JTextField Textbox_ISBN;
    private javax.swing.JTextField Textbox_Judul;
    private javax.swing.JTextField Textbox_Penulis;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
