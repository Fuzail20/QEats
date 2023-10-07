package com.crio.qeats.exchanges;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
    @NoArgsConstructor
    @AllArgsConstructor
    public  class MenuResponse {
        private List<MenuItem> items;
        private String restaurantId;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MenuItem {
        private List<String> attributes;
        private String id;
        private String imageUrl;
        private String itemId;
        private String name;
        private double price;
    }
    }

    
