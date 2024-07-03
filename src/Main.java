
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ThuVien library = ThuVien.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nQuan Ly Thu Vien");
            System.out.println("1. Quan ly sach");
            System.out.println("2. Quan ly thanh vien");
            System.out.println("3. Quan ly thu thu");
            System.out.println("4. Muon sach");
            System.out.println("5. Tra sach");
            System.out.println("6. Xem muon tra sach");
            System.out.println("0. Thoat");
            System.out.print("Chon di : ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageBooksMenu(library, scanner);
                    break;
                case 2:
                    manageMembersMenu(library, scanner);
                    break;
                case 3:
                    manageLibrariansMenu(library, scanner);
                    break;
                case 4:
                    borrowBookMenu(library, scanner);
                    break;
                case 5:
                    returnBookMenu(library, scanner);
                    break;
                case 6:
                    viewBorrowingsMenu(library);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Chon lai");
            }
        }
    }

    private static void manageBooksMenu(ThuVien library, Scanner scanner) {
        while (true) {
            System.out.println("\nQuan ly sach");
            System.out.println("1. them sach");
            System.out.println("2. xoa sach");
            System.out.println("3. cap nhat sach");
            System.out.println("4. tim sach");
            System.out.println("0. quay ve");
            System.out.print("Chon di : ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Nhap masach: ");
                    String masach = scanner.nextLine();
                    System.out.print("nhap ten: ");
                    String ten = scanner.nextLine();
                    System.out.print("nhap tacgia: ");
                    String tacgia = scanner.nextLine();
                    System.out.print("nhap the loai: ");
                    String theloai = scanner.nextLine();
                    System.out.print("nhap so luong: ");
                    int soluong = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    library.addBook(new Book(masach, ten, tacgia, theloai, soluong));
                    
                    break;
                case 2:
                    System.out.print("Nhap ma sach muon xoa: ");
                    String isbnToRemove = scanner.nextLine();
                    library.removeBook(isbnToRemove);
//                    System.out.println("Book removed successfully.");
                    break;
                case 3:
                    System.out.print("Nhap ma sach muon cap nhat: ");
                    String isbnToUpdate = scanner.nextLine();
                    System.out.print("Nhap ten : ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Nhap tacgia: ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Nhap theloai: ");
                    String newGenre = scanner.nextLine();
                    System.out.print("Nhap so luong (enter 1 to keep current): ");
                    int newCopies = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    library.updateBook(isbnToUpdate, newTitle.isEmpty() ? null : newTitle,
                            newAuthor.isEmpty() ? null : newAuthor,
                            newGenre.isEmpty() ? null : newGenre,
                            newCopies == 1 ? 1 : newCopies);
                    
                    break;
                case 4:
                    System.out.print("Nhap tu khoa: ");
                    String keyword = scanner.nextLine();
                    System.out.println("Ket qua:");
                    for (Book book : library.searchBooks(keyword)) {
                        System.out.println(book);
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Nhap lai.");
            }
        }
    }

    private static void manageMembersMenu(ThuVien library, Scanner scanner) {
        while (true) {
            System.out.println("\nQuan ly thanh vien");
            System.out.println("1.Them thanh vien");
            System.out.println("2. Xoa thanh vien");
            System.out.println("3. cap nhat thanh vien");
            System.out.println("4. tim thanh vien");
            System.out.println("0. Back");
            System.out.print("Chon di: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    System.out.print("Nhap ma thanhvien: ");
                    String mathanhvien = scanner.nextLine();
                    System.out.print("Nhap ten: ");
                    String ten = scanner.nextLine();
                    System.out.print("Nhap dia chi: ");
                    String diachi = scanner.nextLine();
                    library.addMember(new Member(mathanhvien, ten, diachi));
                    
                    break;
                case 2:
                    System.out.print("Nhap ma thanh vien muon xoa: ");
                    String memberIdToRemove = scanner.nextLine();
                    library.removeMember(memberIdToRemove);
                   
                    break;
                case 3:
                    System.out.print("Nhap ma tv muon cap nhat: ");
                    String memberIdToUpdate = scanner.nextLine();
                    System.out.print("nhap ten: ");
                    String newName = scanner.nextLine();
                    System.out.print("Nhap diachi: ");
                    String newAddress = scanner.nextLine();
                    library.updateMember(memberIdToUpdate,
                            newName.isEmpty() ? null : newName,
                            newAddress.isEmpty() ? null : newAddress);
                    
                    break;
                case 4:
                    System.out.print("Nhap tu khoa: ");
                    String keyword = scanner.nextLine();
                    System.out.println("Ket qua:");
                    for (Member member : library.searchMembers(keyword)) {
                        System.out.println(member);
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Nhap lai.");
            }
        }
    }

    private static void manageLibrariansMenu(ThuVien library, Scanner scanner) {
        while (true) {
            System.out.println("\nQuan ly thu thu");
            System.out.println("1. Them tt");
            System.out.println("2. xoa tt");
            System.out.println("3. cap nhat tt");
            System.out.println("4. tim tt");
            System.out.println("0. Back");
            System.out.print("Chon di: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Nhap ma tt: ");
                    String maso = scanner.nextLine();
                    System.out.print("ten: ");
                    String ten = scanner.nextLine();
                    System.out.print("ca lam viec: ");
                    String calam = scanner.nextLine();
                    library.addLibrarian(new Librarian(maso, ten, calam));
                    
                    break;
                case 2:
                    System.out.print("nhap ma tt muon xoa: ");
                    String librarianIdToRemove = scanner.nextLine();
                    library.removeLibrarian(librarianIdToRemove);
                    
                    break;
                case 3:
                    System.out.print("nhap ma tt muon cap nhat: ");
                    String librarianIdToUpdate = scanner.nextLine();
                    System.out.print("ten moi: ");
                    String newName = scanner.nextLine();
                    System.out.print("ca lam moi: ");
                    String newShift = scanner.nextLine();
                    library.updateLibrarian(librarianIdToUpdate,
                            newName.isEmpty() ? null : newName,
                            newShift.isEmpty() ? null : newShift);
                    
                    break;
                case 4:
                    System.out.print("Nhap tu khoa: ");
                    String keyword = scanner.nextLine();
                    System.out.println("Ket qua:");
                    for (Librarian librarian : library.searchLibrarian(keyword)) {
                        System.out.println(librarian);
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Nhap lai");
            }
        }
    }

    private static void borrowBookMenu(ThuVien library, Scanner scanner) {
        System.out.print("Nhap ma thanh vien: ");
        String mathanhvien = scanner.nextLine();
        System.out.print("Nhap ma sach: ");
        String masach = scanner.nextLine();
        library.muonsach(mathanhvien, masach);
        
    }

    private static void returnBookMenu(ThuVien library, Scanner scanner) {
        System.out.print("Nhap ma thanh vien: ");
        String mathanhvien = scanner.nextLine();
        System.out.print("nhap ma sach: ");
        String masach = scanner.nextLine();
        library.trasach(mathanhvien, masach);
        
    }

    private static void viewBorrowingsMenu(ThuVien library) {
        System.out.println("\nThong tin muon,tra sach :");
        for (Borrowing borrowing : library.getBorrowings()) {
            System.out.println(borrowing);
        }
    }
}
