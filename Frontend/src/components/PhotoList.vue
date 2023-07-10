<script>
import Photo from "./Photo.vue";
import ContactForm from "./ContactForm.vue";
import axios from "axios";

export default {
  data() {
    return {
      query: "",
      endpoint: "http://localhost:5174/api/v1/photos",
      photoList: [],
      categoryList: [],
    };
  },
  components: { Photo, ContactForm },

  methods: {
    fetchPhotoList() {
      axios.get(this.endpoint).then((response) => {
        this.photoList = response.data.photos;
        this.categoryList = response.data.categories;
        console.log(response.data.categories);
      });
    },
    searchPhoto() {
      const url = this.query ? `${this.endpoint}?keyword=${this.query}` : this.endpoint;
      axios.get(url).then((response) => {
        this.photoList = response.data.photos;
        console.log(this.photoList);
      });
    },
  },
  created() {
    this.fetchPhotoList();
  },
};
</script>

<template>
  <h1 class="my-4">Portfolio</h1>
  <div class="d-flex justify-content-between align-items-center">
    <form @submit.prevent="searchPhoto" class="d-flex justify-content-end">
      <div class="input-group mb-3">
        <input type="search" class="form-control" placeholder="Cerca per titolo" v-model="query" />
        <button class="btn btn-outline-primary" type="submit">Cerca</button>
      </div>
    </form>
  </div>
  <div v-if="this.photoList.length" class="row gy-5">
    <Photo v-for="photo in this.photoList" :key="photo.id" :photo="photo" class="col-4 d-flex" />
  </div>
  <h2 v-else class="">Non ci sono foto</h2>

  <h3 class="my-5">Contatti</h3>
  <ContactForm />
</template>

<style lang="scss" scoped></style>
