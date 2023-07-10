<template>
  <div>
    <nav class="navbar navbar-dark navbar-expand-md dark">
      <div class="container">
        <a class="navbar-brand d-flex align-items-center" href="#"
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
          <ul class="navbar-nav justify-content-between mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="http://localhost:5174/login"
                >Creators</a
              >
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
nav,
.navbar-collapse {
  height: 70px;
  background-color: #171717;
  .navbar-brand {
    height: 70px;
    .nav-logo {
      max-height: 50px;
    }
  }
}
</style>
