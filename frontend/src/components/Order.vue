<template>
    <div>
        <String
            label="FoodId"
            v-model="value.foodId"
            :editMode="editMode"
        />
        <v-divider class="border-opacity-50 my-divider my-2"></v-divider>
        <div variant="outlined" class="my-2">
            <String
                label="Option"
                v-model="optionInput"
                :editMode="editMode"
                @keydown.enter="addOptionList"
            />
            <v-card v-if="value.option.length > 0" variant="outlined" class="pa-4 mt-2">
                <v-card-sub-title>
                    Option List
                </v-card-sub-title>
                <li v-for="(name, index) in value.option" :key="index">
                    {{ name }}
                </li>
            </v-card>
            <v-row class="ma-0 pa-0 mt-2">
                <v-spacer></v-spacer>
                <v-btn @click="addOptionList">
                    Option 추가
                </v-btn>
            </v-row>
            
            <StringDetailGrid
                label="Option"
                offline
                v-model="value.option"
                :editMode="editMode"
                @change="change"
            />
        </div>
        <v-divider class="border-opacity-50 my-divider my-2"></v-divider>
        <String
            label="Address"
            v-model="value.address"
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
    name: 'Order',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "orders",
        optionInput: null,
        value: {
            option: [],
        }
    }),
    created(){
    },
    computed:{
    },
    methods: {
        addOptionList() {
            if (this.optionInput !== null && this.optionInput !== '') {
                this.value.option.push(this.optionInput);
                this.optionInput = null; // null로 초기화
            }
        },
    },
}
</script>
