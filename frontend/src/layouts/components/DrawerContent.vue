<script setup>
import {
  VerticalNavLink,
  VerticalNavSectionTitle,
} from '@layouts'
</script>

<template>
    <ul>
        <VerticalNavSectionTitle :item="{ heading: 'Front' }" />
        <VerticalNavLink
            :item="{
                title: 'Order',
                to: '/orders',
            }"
        />
        <VerticalNavLink
            :item="{
                title: 'Payment',
                to: '/payments',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: 'Store' }" />
        <VerticalNavLink
            :item="{
                title: 'FoodCooking',
                to: '/foodCookings',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: 'Rider' }" />
        <VerticalNavLink
            :item="{
                title: 'Delivery',
                to: '/deliveries',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: 'Customer' }" />
        <VerticalNavLink
            :item="{
                title: 'MyPage',
                to: '/myPages',
            }"
        />
    </ul>
</template>
