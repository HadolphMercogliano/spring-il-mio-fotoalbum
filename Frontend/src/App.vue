<script>
import AppHeader from "./components/AppHeader.vue";
import AppMain from "./components/AppMain.vue";
import AppFooter from "./components/AppFooter.vue";
export default {
  name: "PhotoBook",
  data() {
    return {
      title: "PhotoBook App",
    };
  },
  created() {
    document.title = this.$options.name;
  },

  components: { AppMain, AppHeader, AppFooter },
};
</script>
<template>
  <div class="body d-flex flex-column">
    <header>
      <AppHeader />
    </header>
    <main class="container">
      <AppMain></AppMain>
    </main>
    <footer class="mt-auto"><AppFooter /></footer>
  </div>
</template>

<style lang="scss">
.body {
  height: 100vh;
}
.dark {
  * {
    color: rgb(196, 196, 196) !important;
  }
}
</style>
