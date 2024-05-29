import java.time.LocalDate;
import java.util.*;

public class MaterialManage {
    private List<Material> materials = new ArrayList<>();

    public void addMaterials(Material material) {
            materials.add(material);
    }

    public boolean isExistID(String id) {
        for (Material material : materials) {
            if (material.getId().equals(id)) {
                System.out.println("ID: " + id + " is exist");
                return false;
            }
        }
        return true;
    }

    public void removeMaterials(Material material) {
        materials.remove(material);
    }

    public void editMaterials(Material material,Material newMaterial) {
        int index = materials.indexOf(material);
        if(index != -1) {
            materials.set(index,newMaterial);
        }
    }

    public List<Material> getMaterials() {
        return materials;
    }


    public double getTotalAmount(){
        double total = 0;
        for (Material material : materials) {
            total += material.getAmount();
        }
        return total;
    }

    public double getTotalDiscountAmount(){
        double total = 0;
        for (Material material : materials) {
            if (material instanceof Discount) {
                total+= ((Discount) material).getRealMoney();
            }
        }
        return total;
    }

    public void sortMaterialsByCost(){
        Comparator<Material> cmp = new Comparator<Material>() {

            @Override
            public int compare(Material o1, Material o2) {
                return Double.compare(o1.getAmount(), o2.getAmount());
            }
        };
        materials.sort(cmp);
    }

    public double compareOriginalvsDiscount(){
        return getTotalAmount() - getTotalDiscountAmount();
    }

    public void displayMaterials(){
        for (Material material : materials) {
            System.out.println(material.toString()+"\n");
        }
    }

}
