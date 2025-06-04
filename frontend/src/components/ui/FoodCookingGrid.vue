<template>
    <v-container>
        <v-snackbar
            v-model="snackbar.status"
            :timeout="snackbar.timeout"
            :color="snackbar.color"
        >
            
            <v-btn style="margin-left: 80px;" text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
        <div class="panel">
            <div class="gs-bundle-of-buttons" style="max-height:10vh;">
                <v-btn @click="addNewRow" @class="contrast-primary-text" small color="primary">
                    <v-icon small style="margin-left: -5px;">mdi-plus</v-icon>등록
                </v-btn>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="openEditDialog()" class="contrast-primary-text" small color="primary">
                    <v-icon small>mdi-pencil</v-icon>수정
                </v-btn>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="acceptDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('store owner')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>accept
                </v-btn>
                <v-dialog v-model="acceptDialog" width="500">
                    <Accept
                        @closeDialog="acceptDialog = false"
                        @accept="accept"
                    ></Accept>
                </v-dialog>
            </div>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>Status</th>
                        <th>FoodId</th>
                        <th>OrderId</th>
                        <th>Options</th>
                        <th>StoreId</th>
                        <th>CustomerId</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="Status">{{ val.status }}</td>
                            <td class="whitespace-nowrap" label="FoodId">{{ val.foodId }}</td>
                            <td class="whitespace-nowrap" label="OrderId">{{ val.orderId }}</td>
                            <td class="whitespace-nowrap" label="Options">
                                <span v-for="(name, index) in val.options" :key="index">
                                    {{ name }}<br>
                                </span>
                            </td>
                            <td class="whitespace-nowrap" label="StoreId">{{ val.storeId }}</td>
                            <td class="whitespace-nowrap" label="CustomerId">{{ val.customerId }}</td>
                            <v-row class="ma-0 pa-4 align-center">
                                <v-spacer></v-spacer>
                                <Icon style="cursor: pointer;" icon="mi:delete" @click="deleteRow(val)" />
                            </v-row>
                        </tr>
                    </tbody>
                </v-table>
            </div>
            <StringDetailGrid style="margin-top: 20px;" label="Options" offline v-if="selectedRow" v-model="selectedRow.options" :selectedRow="selectedRow"/>
        </div>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0 pa-4"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">FoodCooking 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <FoodCooking :offline="offline"
                            :isNew="!value.idx"
                            :editMode="true"
                            :inList="false"
                            v-model="newValue"
                            @add="append"
                        />
                    </v-card-text>
                </v-card>
            </v-dialog>
            <v-dialog
                v-model="editDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0 pa-4"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">FoodCooking 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <String label="Status" v-model="selectedRow.status" :editMode="true"/>
                            <String label="FoodId" v-model="selectedRow.foodId" :editMode="true"/>
                            <String label="OrderId" v-model="selectedRow.orderId" :editMode="true"/>
                            < label="Options" v-model="selectedRow.options" :editMode="true"/>
                            <String label="StoreId" v-model="selectedRow.storeId" :editMode="true"/>
                            <String label="CustomerId" v-model="selectedRow.customerId" :editMode="true"/>
                            <StringDetailGrid label="Options" offline v-model="selectedRow.options" :editMode="true"/>
                            <v-divider class="border-opacity-100 my-divider"></v-divider>
                            <v-layout row justify-end>
                                <v-btn
                                    width="64px"
                                    color="primary"
                                    @click="save"
                                >
                                    수정
                                </v-btn>
                            </v-layout>
                        </div>
                    </v-card-text>
                </v-card>
            </v-dialog>
        </v-col>
    </v-container>
</template>

<script>
import { ref } from 'vue';
import { useTheme } from 'vuetify';
import BaseGrid from '../base-ui/BaseGrid.vue'


export default {
    name: 'foodCookingGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'foodCookings',
        acceptDialog: false,
    }),
    watch: {
    },
    methods:{
        async accept(params){
            try{
                var path = "accept".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','accept 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.acceptDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>