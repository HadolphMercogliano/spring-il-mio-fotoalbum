<script>
export default {
  name: "PostCard",

  props: {
    photo: Object,
  },
};
</script>

<template>
  <div>
    <div class="card shadow h-100 w-100">
      <div class="card-top flex-grow-1">
        <img
          class="img-fluid card-img-top"
          :src="'http://127.0.0.1:5174/files/image/' + photo.id"
          alt="" />
      </div>
      <div class="card-body p-1">
        <h3>{{ photo.title }}</h3>
        <div>
          <p>{{ photo.description }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped></style>
