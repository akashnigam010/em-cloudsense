package in.cw.csense.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import in.cw.csense.app.entity.Bill;
import in.cw.sense.api.bo.bill.dto.BillDto;
import in.cw.sense.api.bo.bill.dto.ChargeDto;
import in.cw.sense.api.bo.bill.dto.DiscountDto;
import in.cw.sense.api.bo.bill.dto.ItemCountDto;
import in.cw.sense.api.bo.bill.dto.RateValueDto;
import in.cw.sense.api.bo.bill.dto.TotalDto;
import in.cw.sense.api.bo.bill.dto.TypeValueDto;
import in.cw.sense.api.bo.bill.entity.ChargeEntity;
import in.cw.sense.api.bo.bill.entity.DiscountEntity;
import in.cw.sense.api.bo.bill.entity.ItemCountEntity;
import in.cw.sense.api.bo.bill.entity.OrderUnit;
import in.cw.sense.api.bo.bill.entity.RateValueEntity;
import in.cw.sense.api.bo.bill.entity.TotalEntity;
import in.cw.sense.api.bo.bill.entity.TypeValueEntity;
import in.cw.sense.api.bo.table.dto.ItemDto;

@Component
public class BillMapper {
	public void mapBillDtoToEntity(BillDto dto, Bill entity) {
		entity.setCreatedDateTime(dto.getCreatedDateTime());
		entity.setCreatedDateTimeToDisplay(dto.getCreatedDateTime().toString());
		entity.setDiscount(mapDiscountDtoToEntity(dto.getDiscount()));
		entity.setGrandTotal(dto.getGrandTotal());
		entity.setBillId(dto.getId());
		entity.setInternalCharges(mapChargeDtoToEntity(dto.getInternalCharges()));
		entity.setItemCount(mapItemCountDtoToEntity(dto.getItemCount()));
		entity.setOrders(mapOrderItemDtoToEntity(dto.getOrders()));
		entity.setPaymentMode(dto.getPaymentMode());
		entity.setPersonName(dto.getPersonName());
		entity.setReasonForCancel(dto.getReasonForCancel());
		entity.setSettledDateTime(dto.getSettledDateTime());
		entity.setSettledDateTimeToDisplay(dto.getSettledDateTime().toString());
		entity.setStatus(dto.getStatus());
		entity.setSubTotal(mapTotalDtoToEntity(dto.getSubTotal()));
		entity.setSubTotalExclusive(mapTotalDtoToEntity(dto.getSubTotalExclusive()));
		entity.setSubTotalInclusive(mapTotalDtoToEntity(dto.getSubTotalInclusive()));
		entity.setTableNumber(dto.getTableNumber());
		entity.setTax(mapChargeDtoToEntity(dto.getTaxes()));
	}

	public DiscountEntity mapDiscountDtoToEntity(DiscountDto dto) {
		DiscountEntity entity = new DiscountEntity();
		if (dto != null) {
			entity.setFnb(mapTypeValueDtoToEntity(dto.getFnb()));
			entity.setLiquor(mapTypeValueDtoToEntity(dto.getBar()));
		}
		return entity;
	}

	public TypeValueEntity mapTypeValueDtoToEntity(TypeValueDto dto) {
		TypeValueEntity entity = new TypeValueEntity();
		if (dto != null) {
			entity.setAmount(dto.getAmount());
			entity.setType(dto.getDiscountType());
			entity.setValue(dto.getValue());
		}
		return entity;
	}

	public List<ChargeEntity> mapChargeDtoToEntity(List<ChargeDto> dtos) {
		List<ChargeEntity> entities = new ArrayList<>();
		if (dtos != null) {
			for (ChargeDto dto : dtos) {
				ChargeEntity entity = new ChargeEntity();
				entity.setName(dto.getName());
				entity.setFnb(mapRateValueDtoToEntity(dto.getFnb()));
				entity.setLiquor(mapRateValueDtoToEntity(dto.getLiquor()));
				entities.add(entity);
			}
		}
		return entities;
	}

	public RateValueEntity mapRateValueDtoToEntity(RateValueDto dto) {
		RateValueEntity rateValue = new RateValueEntity();
		if (dto != null) {
			rateValue.setRate(dto.getRate());
			rateValue.setValue(dto.getValue());
		}
		return rateValue;
	}

	public ItemCountEntity mapItemCountDtoToEntity(ItemCountDto dto) {
		ItemCountEntity entity = new ItemCountEntity();
		if (dto != null) {
			entity.setFnbItemCount(dto.getFnb());
			entity.setLiqourItemCount(dto.getLiquor());
		}
		return entity;
	}

	public List<OrderUnit> mapOrderItemDtoToEntity(List<ItemDto> items) {
		List<OrderUnit> orderUnits = new ArrayList<>();
		if (items != null) {
			for (ItemDto dto : items) {
				OrderUnit unit = new OrderUnit();
				unit.setItemId(dto.getId());
				unit.setItemName(dto.getName());
				unit.setPrice(dto.getPrice());
				unit.setQuantity(dto.getQuantity());
				unit.setType(dto.getType());
				unit.setValue(dto.getValue());
				orderUnits.add(unit);
			}
		}
		return orderUnits;
	}

	public TotalEntity mapTotalDtoToEntity(TotalDto dto) {
		TotalEntity entity = new TotalEntity();
		if (dto != null) {
			entity.setName(dto.getName());
			entity.setFnb(dto.getFnb());
			entity.setLiquor(dto.getLiquor());
		}
		return entity;
	}
}
