package com.example.sharding;

import com.example.constant.CommonConstants;
import com.google.common.collect.Range;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

/**
 * 类描述：表分片算法
 * @author xiaoj
 */
@Slf4j
public class TableShardingAlgorithm  implements StandardShardingAlgorithm<String> {

    @Override
    public String getType() {
        return "tableShardingAlgorithm";
    }


    /**
     * 精确分片算法
     *
     * @param availableTargetNames 所有配置的表列表，这里代表所匹配到库的所有表
     * @param shardingValue        分片值，也就是create_date的值
     * @return 所匹配表的结果
     */
    @SneakyThrows
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        long value = DateUtils.parseDate(shardingValue.getValue()).getTime();
        if (value <= 0) {
            throw new UnsupportedOperationException("preciseShardingValue is null");
        }
        String suffixWithYear = ShardingAlgorithmDateUtil
                .getPeriodScopeSuffixWithYear(value, 1, CommonConstants.UNDERLINE);
        Optional<String> optional = availableTargetNames.parallelStream()
                .filter(item -> item.endsWith(suffixWithYear))
                .findAny();
        if (optional.isPresent()) {
                return optional.get();
        }
        throw new UnsupportedOperationException("No available tables according input time:" + suffixWithYear);
    }


    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
            RangeShardingValue<String> rangeShardingValue) {
        Range<String> range = rangeShardingValue.getValueRange();
        String startTime = ShardingAlgorithmDateUtil
                .getPeriodScopeSuffixWithYear(DateUtils.parseDate(range.lowerEndpoint()).getTime(), 1, CommonConstants.UNDERLINE);
        String endTime = ShardingAlgorithmDateUtil
                .getPeriodScopeSuffixWithYear(DateUtils.parseDate(range.upperEndpoint()).getTime(), 1, CommonConstants.UNDERLINE);
        int startSuffixNum = Integer.parseInt(startTime.replace(CommonConstants.UNDERLINE, CommonConstants.EMPTY));
        int endSuffixNum = Integer.parseInt(endTime.replace(CommonConstants.UNDERLINE, CommonConstants.EMPTY));
        int maxScopeNum = ShardingAlgorithmDateUtil.calcMaxScopeSuffixWithoutYear(1);
        Collection<String> targetTable = new HashSet<>();
        for (int i = startSuffixNum; i <= endSuffixNum; i++) {
            String str = String.valueOf(i);
            String year = str.substring(0, 4);
            String month = str.substring(4);
            if (maxScopeNum >= Integer.parseInt(month) && !Objects.equals(month, "00")) {
                Optional<String> optional = availableTargetNames.stream()
                        .filter(item -> item.endsWith(year + CommonConstants.UNDERLINE + month)).findFirst();
                optional.ifPresent(tableName -> {
                    try {
                            targetTable.add(tableName);
                    } catch (Exception e) {
                        log.error("collection table doSharding failed.", e);
                    }
                });
            }
        }
        return targetTable;
    }

}
