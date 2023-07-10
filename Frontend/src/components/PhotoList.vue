<script>
import Photo from "./Photo.vue";
import ContactForm from "./ContactForm.vue";
import axios from "axios";

export default {
  data() {
    return {
      endpoint: "http://localhost:5174/api/v1/photos",
      photoList: [],
    };
  },
  components: { Photo, ContactForm },

  methods: {
    fetchPhotoList() {
      axios.get(this.endpoint).then((response) => {
        this.photoList = response.data;
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
  <div v-if="this.photoList.length" class="row">
    <Photo v-for="photo in this.photoList" :key="photo.id" :photo="photo" class="col-4 d-flex" />
  </div>
  <h2 v-else class="">Non ci sono foto</h2>

  <h3 class="my-5">Contatti</h3>
  <ContactForm />
</template>

<style lang="scss" scoped></style>
