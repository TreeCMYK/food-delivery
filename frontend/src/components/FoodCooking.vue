<template>
    <div>
        <String
            label="Status"
            v-model="value.status"
            :editMode="editMode"
        />
        <String
            label="FoodId"
            v-model="value.foodId"
            :editMode="editMode"
        />
        <String
            label="OrderId"
            v-model="value.orderId"
            :editMode="editMode"
        />
        <v-divider class="border-opacity-50 my-divider my-2"></v-divider>
        <div variant="outlined" class="my-2">
            <String
                label="Options"
                v-model="optionsInput"
                :editMode="editMode"
                @keydown.enter="addOptionsList"
            />
            <v-card v-if="value.options.length > 0" variant="outlined" class="pa-4 mt-2">
                <v-card-sub-title>
                    Options List
                </v-card-sub-title>
                <li v-for="(name, index) in value.options" :key="index">
                    {{ name }}
                </li>
            </v-card>
            <v-row class="ma-0 pa-0 mt-2">
                <v-spacer></v-spacer>
                <v-btn @click="addOptionsList">
                    Options 추가
                </v-btn>
            </v-row>
            
            <StringDetailGrid
                label="Options"
                offline
                v-model="value.options"
                :editMode="editMode"
                @change="change"
            />
        </div>
        <v-divider class="border-opacity-50 my-divider my-2"></v-divider>
        <String
            label="StoreId"
            v-model="value.storeId"
            :editMode="editMode"
        />
        <String
            label="CustomerId"
            v-model="value.customerId"
            :editMode="editMode"
        />
        <v-row class="ma-0 pa-0">
            <v-spacer></v-spacer>
            <v-btn width="64px" color="primary" @click="save">
                저장
            </v-btn>
        </v-row>
    </div>
</template>


<script>
import BaseEntity from './base-ui/BaseEntity.vue'

export default {
    name: 'FoodCooking',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "foodCookings",
        optionsInput: null,
        value: {
            options: [],
        }
    }),
    created(){
    },
    computed:{
    },
    methods: {
        addOptionsList() {
            if (this.optionsInput !== null && this.optionsInput !== '') {
                this.value.options.push(this.optionsInput);
                this.optionsInput = null; // null로 초기화
            }
        },
    },
}
</script>
