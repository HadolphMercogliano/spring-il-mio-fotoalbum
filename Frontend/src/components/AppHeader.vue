<template>
  <div>
    <nav class="navbar navbar-expand-md dark">
      <div class="container">
        <a class="navbar-brand" href="#"
          ><img class="nav-logo" src="../../public/assets/logo.png" alt=""
        /></a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#">Home</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  setup() {
    return {};
  },
};
</script>

<style lang="scss" scoped>
nav {
  height: 70px;
  background-color: #171717;
  .navbar-brand {
    height: 70px;
    .nav-logo {
      height: 100%;
    }
  }
}
</style>
