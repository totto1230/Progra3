/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  fernando
 * Created: Apr 7, 2022
 */

ALTER TABLE `PrograIII`.`Bookeo` 
CHANGE COLUMN `totalVenta` `totalVenta` DECIMAL(19,2) NOT NULL ;

ALTER TABLE `PrograIII`.`BookeoAtracciones` 
CHANGE COLUMN `idenAtrac` `idAtracciones` INT NOT NULL ;
