
CREATE PROCEDURE Bookeo_TraerPorFechas(
	In prmTicket int,
    In prmCedula int,
    In prmDesde datetime,
    In prmHasta datetime
)
BEGIN
Select ticket,b.cedula,fechaCompra,fechaVisita,totalVenta,paseEspecial 
from Bookeo b 
where -1 != prmCedula 
and (case when prmTicket = -1 then b.ticket > 1 else b.ticket = prmTicket end) 
and fechaVisita between prmDesde and prmHasta;
END;



CREATE PROCEDURE Bookeo_TraerPorFechasCliente(
	In prmTicket int,
    In prmCedula int,
    In prmDesde datetime,
    In prmHasta datetime
)
BEGIN
 set @filtro := 1; 
 Select b.ticket,b.cedula,fechaCompra,fechaVisita,totalVenta,paseEspecial from Bookeo b
 inner join BookeoPersona bp 
 on b.ticket = bp.ticket 
 where bp.cedula = prmCedula 
 and (case when prmTicket = -1 then b.ticket > 1 else b.ticket = prmTicket end) 
 and fechaVisita between prmDesde and prmHasta;
END;

