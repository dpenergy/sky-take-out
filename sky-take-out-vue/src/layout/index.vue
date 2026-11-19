<script setup lang="ts">
</script>
<template>
    <h1>head</h1>
    <h1>aside
        <div><RouterLink to="/login">login</RouterLink></div>
        <div><RouterLink to="workbench">workbench</RouterLink></div>
        <div><RouterLink to="admi">admi</RouterLink></div>
        <div><RouterLink to="order">order</RouterLink></div>
        <div><RouterLink to="customer">customer</RouterLink></div>
    </h1>
    <div>aside</div>
    <div>main- <RouterView/> -main</div>
</template>
<style scoped>
</style>