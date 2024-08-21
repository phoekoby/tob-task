package com.company.service;

import com.company.dto.InputDto;
import com.company.dto.OutputDto;
import com.company.service.interfaces.BusinessLogic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class RequestService implements BusinessLogic<InputDto, OutputDto> {
    private final Map<Integer, TreeMap<Long, Integer>> buyMapInstrumentToMapBestPriceAmount = new HashMap<>();
    private final Map<Integer, TreeMap<Long, Integer>> sellMapInstrumentToMapBestPriceAmount = new HashMap<>();

    public OutputDto process(InputDto inputDto) {
        if ('B' == inputDto.getSide()) {
            return processBuy(inputDto);
        }
        if ('S' == inputDto.getSide()) {
            return processSell(inputDto);
        }
        throw new IllegalArgumentException("Сторона заявки может принимать значения B и S");
    }


    public OutputDto processSell(InputDto inputDto) {
        Integer instrumentId = inputDto.getInstrumentId();
        sellMapInstrumentToMapBestPriceAmount.computeIfAbsent(instrumentId, integer -> {
            TreeMap<Long, Integer> treeMap = new TreeMap<>(Long::compareTo);
            treeMap.put(999999999999999999L, 0);
            return treeMap;
        });
        return recalculateBestPrice(inputDto, instrumentId, sellMapInstrumentToMapBestPriceAmount);
    }

    public OutputDto processBuy(InputDto inputDto) {
        Integer instrumentId = inputDto.getInstrumentId();
        buyMapInstrumentToMapBestPriceAmount.computeIfAbsent(instrumentId, integer -> {
            TreeMap<Long, Integer> treeMap = new TreeMap<>((price1, price2) -> Math.toIntExact(price2 - price1));
            treeMap.put(0L, 0);
            return treeMap;
        });
        return recalculateBestPrice(inputDto, instrumentId, buyMapInstrumentToMapBestPriceAmount);
    }

    private OutputDto recalculateBestPrice(
            InputDto inputDto,
            Integer instrumentId,
            Map<Integer, TreeMap<Long, Integer>> instrumentToMapBestPriceAmount
    ) {
        TreeMap<Long, Integer> bestPriceMap = instrumentToMapBestPriceAmount.get(instrumentId);
        Long bestPriceBefore = bestPriceMap.firstKey();
        Integer amountBefore = bestPriceMap.get(bestPriceBefore);
        if ('0' == inputDto.getAction()) {
            bestPriceMap.put(inputDto.getPrice(), bestPriceMap.getOrDefault(inputDto.getPrice(), 0) + inputDto.getAmount());
        } else if ('1' == inputDto.getAction()) {
            bestPriceMap.remove(inputDto.getPrice());
        } else if ('2' == inputDto.getAction()) {
            int amount = bestPriceMap.get(inputDto.getPrice()) - inputDto.getAmount();
            if (0 >= amount) {
                bestPriceMap.remove(inputDto.getPrice());
            } else {
                bestPriceMap.put(inputDto.getPrice(), amount);
            }
        } else {
            throw new IllegalArgumentException("Действие с заявкой может принимать только значений 0,1,2");
        }
        Long bestPriceAfter = bestPriceMap.firstKey();
        Integer amountAfter = bestPriceMap.get(bestPriceAfter);
        if (Objects.equals(bestPriceAfter, bestPriceBefore) && Objects.equals(amountBefore, amountAfter)) {
            return null;
        }
        return OutputDto.builder()
                .instrumentId(instrumentId)
                .side(inputDto.getSide())
                .amount(bestPriceMap.get(bestPriceAfter))
                .price(bestPriceAfter)
                .build();
    }
}
