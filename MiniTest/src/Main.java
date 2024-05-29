import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MaterialManage manage = new MaterialManage();
        int choice;
        do{
            System.out.println("1-Add material");
            System.out.println("2-Delete material");
            System.out.println("3-Edit material");
            System.out.println("4-Display materials list");
            System.out.println("5-Calculate total amount of materials");
            System.out.println("6-Sort materials by amount");
            System.out.println("7-Calculate difference not discount vs discount");
            System.out.println("0-Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
                    System.out.print("Input material type(1-CrispyFlour 2-Meat): ");
                    int type = sc.nextInt();
                    sc.nextLine();
                    String id;
                    do{
                        System.out.print("ID: ");
                        id = sc.nextLine();
                    }while(!manage.isExistID(id));
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    LocalDate manufacturingDay=null;
                    do{
                        try{
                            System.out.print("Input manufacturing day(yyyy-mm-dd): ");
                            manufacturingDay = LocalDate.parse(sc.nextLine());
                        }catch (DateTimeParseException e){
                            System.out.println("Invalid date. Try again with format yyyy-mm-dd");
                        }
                    }while(manufacturingDay==null || manufacturingDay.isAfter(LocalDate.now()));

                    System.out.print("Input price: ");
                    int cost = sc.nextInt();

                    if(type == 1){
                        System.out.print("Input quantity: ");
                        int quantity = sc.nextInt();
                        manage.addMaterials(new CrispyFlour(id, name, manufacturingDay, cost, quantity));
                    }else if(type == 2){
                        System.out.print("Input weight: ");
                        double weight = sc.nextDouble();
                        manage.addMaterials(new Meat(id, name, manufacturingDay, cost, weight));
                    }
                    break;
                case 2:
                    System.out.print("Input id you want to delete: ");
                    String removeId = sc.nextLine();
                    Material materialRemove = null;
                    for(Material material : manage.getMaterials()){
                        if(material.getId().equals(removeId)){
                            materialRemove = material;
                            break;
                        }
                    }
                    if(materialRemove != null){
                        manage.removeMaterials(materialRemove);
                        System.out.println("Material deleted!");
                    }else {
                        System.out.println("Material not found!");
                    }
                    break;
                    case 3:
                        System.out.print("Input id you want to edit: ");
                        String editId = sc.nextLine();
                        Material materialEdit = null;
                        for(Material material : manage.getMaterials()){
                            if(material.getId().equals(editId)){
                                materialEdit = material;
                                break;
                            }
                        }

                        if(materialEdit != null){
                            System.out.print("Name: ");
                            String newName = sc.nextLine();
                            materialEdit.setName(newName);
                            System.out.print("Input manufacturing day: ");
                            LocalDate newManufacturingDay = LocalDate.parse(sc.nextLine());
                            materialEdit.setManufactureDate(newManufacturingDay);
                            System.out.print("Input price: ");
                            int newCost = sc.nextInt();
                            materialEdit.setCost(newCost);
                            if(materialEdit instanceof CrispyFlour){
                                System.out.print("Input quantity: ");
                                int newQuantity = sc.nextInt();
                                ((CrispyFlour)materialEdit).setQuantity(newQuantity);
                            }else if(materialEdit instanceof Meat){
                                System.out.print("Input weight: ");
                                double newWeight = sc.nextDouble();
                                ((Meat) materialEdit).setWeight(newWeight);
                            }
                        }else {
                            System.out.println("Material not found!");
                        }
                        break;
                        case 4:
                            manage.displayMaterials();
                            break;
                            case 5:
                                System.out.println("Total amount of materials: " + manage.getTotalAmount());
                                break;
                                case 6:
                                    manage.sortMaterialsByCost();
                                    System.out.println("Materials list sorted by cost");
                                    break;
                                    case 7:
                                        System.out.println("Difference not discount vs discount: "+manage.compareOriginalvsDiscount());
                                        break;
                case 0:
                    System.out.println("Exiting program!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }while(choice != 0);
    }
}
