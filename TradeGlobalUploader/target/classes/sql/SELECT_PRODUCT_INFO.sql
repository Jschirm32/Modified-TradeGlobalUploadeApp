SELECT DISTINCT itm.ITMREF_0 as SKU_ID,itm.YPHASE_0,itm.ITMDES1_0+itm.ITMDES2_0 as Name,itm.ITMDES1_0+itm.ITMDES2_0 as Description,'' as Title,''
as Keywords,'' as Specs,itm.YCOLORFAM_0 as Color,itm.YCRYORI_0 as CountryOfOrigin,'Unisex' as Gender,'' as Material,SUBSTRING(itm.VOU_0,1,2) 
as UOMSize,CASE WHEN SUBSTRING(TCLCOD_0,1,1) = 'P' THEN 'PURCOSMETICS' WHEN SUBSTRING(TCLCOD_0,1,1) = 'C' THEN 'COSMEDIXS' WHEN  SUBSTRING(TCLCOD_0,1,1) = 'B' THEN 'BUTTER' END AS Brand,itm.XA_DTH_0 as Length,itm.XA_WID_0 as Width,itm.XA_HEI_0 as Height,itm.WEU_0 as UOMWeight,itm.ITMWEI_0 as Weight,3304.99 as 
HarmonizedTariffCode,'' as ImageURL,'' as MerchantStyle,'' as ciDesc1,'' as ciDesc2,'' as ciDesc3,'FALSE' as isMasterProduct,'' 
as UseBagPaddedMailer,'' as isHazmat FROM PROD.ITMMASTER itm inner join (select distinct ITMREF_0,IPTDAT_0 from PROD.STOJOU) 
as STOJOU on itm.ITMREF_0= STOJOU.ITMREF_0 WHERE itm.YPHASE_0=2 AND STOJOU.IPTDAT_0 is not null