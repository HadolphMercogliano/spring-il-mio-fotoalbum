<script>
import axios from "axios";
export default {
  data() {
    return {
      endpoint: "http://localhost:5174/api/v1/photos/message",
      contact: {
        email: "",
        message: "",
      },
      errors: [],
      success: false,
    };
  },
  methods: {
    sendMessage() {
      this.errors = [];
      const contact = {
        email: this.contact.email,
        message: this.contact.message,
      };
      axios
        .post(this.endpoint, contact)
        .then((res) => {
          this.contact.email = "";
          this.contact.message = "";

          this.success = true;
          // this.$emit("Messaggio inviato correttamente");
        })
        .catch((err) => {
          const response_errors = err.response.data.errors;
          console.log(response_errors);
          for (const field in response_errors) {
            this.errors.push(response_errors[field].defaultMessage);
          }
        });
      // .finally(() => {});
    },
  },
};
</script>

<template>
  <div>
    <h3 class="my-3 text-center">Invia un messaggio</h3>

    <div v-if="errors.length" class="alert alert-danger" role="alert">
      <ul>
        <li v-for="error in errors">{{ error }}</li>
      </ul>
    </div>

    <div v-if="success" class="alert alert-success" role="alert">
      Il messaggio è stato inviato con successo
    </div>

    <form @submit.prevent="sendMessage">
      <div class="row my-3 d-flex justify-content-center">
        <div class="col-2">
          <label for="contact_email">Email: </label>
        </div>
        <div class="col-8">
          <input
            class="form-control"
            type="email"
            id="contact_email"
            v-model="contact.email"
            required />
        </div>
      </div>

      <div class="row my-3 d-flex justify-content-center">
        <div class="col-2">
          <label for="contact_message"> Messaggio: </label>
        </div>
        <div class="col-8">
          <textarea
            class="form-control"
            v-model="contact.message"
            id="contact_message"
            required></textarea>
        </div>
      </div>

      <div class="row my-3 d-flex justify-content-center">
        <div class="offset-2 col-8">
          <button type="submit" class="btn btn-primary">Invia</button>
        </div>
      </div>
    </form>
  </div>
</template>

<style lang="scss" scoped></style>
