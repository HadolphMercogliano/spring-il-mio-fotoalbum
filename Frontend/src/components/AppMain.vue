<script>
import AppHeader from "./AppHeader.vue";
import PhotoList from "./PhotoList.vue";
export default {
  components: { AppHeader, PhotoList },
};
</script>

<template>
  <header>
    <AppHeader />
  </header>
  <main class="container">
    <PhotoList />
  </main>
</template>

<style lang="scss" scoped></style>
