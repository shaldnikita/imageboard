package ru.shaldnikita.imageboard.port.adapter.model;

import com.google.common.collect.Lists;
import ru.shaldnikita.imageboard.domain.model.Data;
import ru.shaldnikita.imageboard.util.ValidationUtility;

import java.util.List;
import java.util.stream.Collectors;

@lombok.Data
/**
 * required for frontend table 2xN columns/rows splitting
 * -------------
 * | pic | pic |
 * | puc | pic |
 * | pic | pic |
 * -------------
 */
public class DataBatches {

    private List<DataBatch> batches;

    public DataBatches(List<Data> data) {
        this.batches = Lists.partition(data, 2)
                .stream()
                .map(DataBatch::new)
                .collect(Collectors.toList());
    }

    public List<Data> getData(){
        return batches.stream().flatMap(b -> b.getData().stream()).collect(Collectors.toList());
    }


    @lombok.Data
    public static class DataBatch {
        private final List<Data> data;

        public DataBatch(List<Data> data) {
            ValidationUtility.validate (data.size() <3 );
            this.data = data;
        }
    }

}

