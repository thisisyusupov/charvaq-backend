package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Currency {

   private  long id;
   private String Code;
   private String Ccy;
   private String CcyNm_RU;
   private String CcyNm_UZ;
   private String CcyNm_UZC;
   private String CcyNm_EN;
   private String Nominal;
   private String Rate;
   private String Diff;
   private String Date;

}
