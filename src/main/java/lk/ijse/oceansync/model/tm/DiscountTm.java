package lk.ijse.oceansync.model.tm;

public class DiscountTm {

   private String discountId;
   private String type;
   private int discount;

   public DiscountTm(String discountId, String type, int discount) {
      this.discountId = discountId;
      this.type = type;
      this.discount = discount;
   }

   public String getDiscountId() {
      return discountId;
   }

   public void setDiscountId(String discountId) {
      this.discountId = discountId;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public int getDiscount() {
      return discount;
   }

   public void setDiscount(int discount) {
      this.discount = discount;
   }
}
