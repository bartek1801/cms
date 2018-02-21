package pl.com.bottega.cms.domain;

public class CreditCardData {

   private String number;

   private int expirationMonth;

   private int expirationYear;

   private int cvc;

   public CreditCardData() {
   }

   public String getNumber() {
      return number;
   }

   public void setNumber(String number) {
      this.number = number;
   }

   public int getExpirationMonth() {
      return expirationMonth;
   }

   public void setExpirationMonth(int expirationMonth) {
      this.expirationMonth = expirationMonth;
   }

   public int getCvc() {
      return cvc;
   }

   public void setCvc(int cvc) {
      this.cvc = cvc;
   }

   public int getExpirationYear() {
      return expirationYear;
   }

   public void setExpirationYear(int expirationYear) {
      this.expirationYear = expirationYear;
   }
}
