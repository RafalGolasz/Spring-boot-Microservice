package com.example.mikroserwis.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;

@Entity
public class Part {

   @Id
   @GeneratedValue(strategy =  GenerationType.IDENTITY)
   private int serialNumber;
   private int materialNumber;
   private int supplierNumber;
   private int quantity;

   public Part(int materialNumber, int quantity, int supplierNumber) {
      this.materialNumber = materialNumber;
      this.quantity = quantity;
      this.supplierNumber = supplierNumber;
   }

   public Part(int serialNumber, int supplierNumber, int quantity, int materialNumber) {
      this.serialNumber = serialNumber;
      this.supplierNumber = supplierNumber;
      this.quantity = quantity;
      this.materialNumber = materialNumber;
   }

   public Part(int materialNumber, int supplierNumber) {
      this.materialNumber = materialNumber;
      this.supplierNumber = supplierNumber;
      this.quantity = 0;
   }

   public Part() {
   }

   public void setSerialNumber(int serialNumber) {
      this.serialNumber = serialNumber;
   }

   public void setMaterialNumber(int materialNumber) {
      this.materialNumber = materialNumber;
   }

   public void setSupplierNumber(int supplierNumber) {
      this.supplierNumber = supplierNumber;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public int getSerialNumber() {
      return serialNumber;
   }

   public int getMaterialNumber() {
      return materialNumber;
   }

   public int getSupplierNumber() {
      return supplierNumber;
   }

   public int getQuantity() {
      return quantity;
   }
}


