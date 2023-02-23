<template>
  <div>
    <form class="form">
      <div class="form-element">
        <label for="name">Name: </label>
        <input type="text" name="name" v-model="name" />
      </div>
      <div class="form-element">
        <label for="date">Date: </label>
        <input type="date" name="date" v-model="date" />
        <label for="timeslot">Timeslot: </label>
        <select
          name="timeslot"
          v-model="timeslot"
          class="select"
          @change="setTimeSlot(timeslot)"
        >
          <option name="timeslot" v-for="x in timeslots" :key="x.id" :value="x">
            {{ x.from }} - {{ x.to }}, INR {{ x.charge }}
          </option>
        </select>
      </div>
      <div class="form-element">
        <p>Are there at least 22 players?</p>
        <input
          type="radio"
          :value="true"
          name="atLeast22"
          v-model="atLeast22"
        />
        <label for="atLeast22">Yes</label>

        <input
          type="radio"
          :value="false"
          name="atLeast22"
          v-model="atLeast22"
        />
        <label for="atLeast22">No</label>
      </div>
      <div class="form-element">
        <label for="id-type">ID type: </label>
        <select
          name="id-type"
          v-model="idType"
          style="margin-right: 30px"
          class="select"
        >
          <option value="aadhar">Aadhar number</option>
          <option value="passport">Passport number</option>
        </select>
        <label for="id" v-if="idType == 'passport'">Passport Number: </label>
        <label for="id" v-else>Aadhar Number: </label>
        <input type="text" name="id" v-model="id" />
      </div>
      <div class="form-element" v-if="!!timeslot.charge">
        <p>Charge = INR {{ updatedTimeSlot }}</p>
      </div>
      <div class="form-button-container">
        <button type="button" class="form-button" @click="submitForm()">
          Book
        </button>
      </div>
    </form>
    <div class="error-message" v-if="invalid.status">
      <h3>{{ invalid.errMsg }}</h3>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, watch, computed } from "vue";
import { useStore } from "vuex";
import properties from "../properties";

const store = useStore();

const name = ref("");
const timeslot = reactive({
  from: "",
  to: "",
  charge: null,
});
const timeslots = ref([]);
const idType = ref("");
const id = ref("");
const atLeast22 = ref(false);
const invalid = reactive({
  status: false,
  errMsg: "",
});
const date = ref();

const finalValidation = () => {
  if (
    name.value == "" ||
    timeslot.value == "" ||
    timeslot.value == "no slot available" ||
    id.value == "" ||
    id.value == null ||
    !atLeast22.value ||
    !isDateValid(date.value)
  ) {
    invalid.status = true;
    invalid.errMsg = "One or more fields were not filled out properly";
    if (!isDateValid(date.value))
      invalid.errMsg = "Date cannot be today or in the past";
  } else if (idType.value === "aadhar" && !id.value.match("^[2-9][0-9]{11}$")) {
    invalid.status = true;
    invalid.errMsg = "Invalid aadhar number";
  } else {
    invalid.status = false;
  }

  return !invalid.status;
};

async function submitForm() {
  const isValid = finalValidation();
  console.log("Form valid? " + isValid);
  if (!isValid) return;

  const formData = {
    from: date.value + " " + timeslot.from,
    to: date.value + " " + timeslot.to,
    facility: properties.facility,
    userId: id.value,
  };

  console.log(formData);

  const response = await fetch(properties.backendUrl + "api/userOps/booking", {
    method: "POST",
    body: JSON.stringify(formData),
    headers: new Headers({ "content-type": "application/json" }),
  });
  if (response.ok) {
    const resJson = await response.json();
    console.log(resJson);
    store.dispatch("setToken", resJson);
  }
}

function isDateValid(inputDate) {
  const dateNow = new Date();
  const inputDateWrapper = new Date(inputDate);

  return dateNow.getTime() < inputDateWrapper.getTime();
}

watch(date, async (newDate) => {
  const response = await fetch(
    properties.backendUrl +
      `api/userOps/slots?facility=${properties.facility}&date=${newDate}`
  );
  timeslots.value = await response.json();
});

function setTimeSlot(newTimeSlot) {
  timeslot.to = newTimeSlot.to;
  timeslot.from = newTimeSlot.from;
  timeslot.charge = newTimeSlot.charge;
}

const updatedTimeSlot = computed(() => {
  return timeslot.charge;
});
</script>

<style scoped>
.form-element {
  margin: 40px;
}

label {
  color: rgb(39, 39, 39);
}
input {
  width: 200px;
  background-color: rgb(238, 247, 246);
  border-color: rgb(169, 244, 219);
}

.form-button-container {
  text-align: center;
}

.form-button {
  color: rgb(221, 221, 221);
  background-color: rgb(39, 39, 39);
  height: 50px;
  width: 90px;
  font-size: 24px;
}

.form-button:hover {
  color: rgb(39, 39, 39);
  background-color: rgb(221, 221, 221);
}

.select {
  width: 150px;
  border-color: rgb(169, 244, 219);
  background-color: rgb(238, 247, 246);
}
</style>
