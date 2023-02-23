<template>
  <div class="form-card">
    <Card>
      <Form></Form>
    </Card>
  </div>
</template>

<script setup>
import Card from "../components/UI/Card.vue";
import Form from "../components/Form.vue";
</script>

<style scoped>
.form-card {
  position: absolute;
  top: 50%;
  left: 50%;
  margin-right: -50%;
  transform: translate(-50%, -50%);
}
</style>
