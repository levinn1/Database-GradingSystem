import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertMahasiswa {
    private static final String URL = "jdbc:mysql://localhost:3306/afldb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();
        
        System.out.print("Masukkan Nilai: ");
        int nilai = scanner.nextInt();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "INSERT INTO mahasiswa (NIM, Nama, Nilai) VALUES (?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nim);
            preparedStatement.setString(2, nama);
            preparedStatement.setInt(3, nilai);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Mahasiswa baru berhasil ditambahkan!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static String determineGrade(int nilai){
        if (nilai >= 90){
            return "A";
        }
        else if (nilai >= 85){
            return "A-";
        }
        else if (nilai >= 80){
            return "B+";
        }
        else if (nilai >= 75){
            return "B";
        }
        else if (nilai >= 70){
            return "B-";
        }
        else if (nilai >= 60){
            return "C+";
        }
        else if (nilai >= 55){
            return "C";

        }else if (nilai >= 45){
            return "D";
        }
        else {
            return "E";
        }
    }
}
