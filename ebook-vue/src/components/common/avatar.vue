<template>
  <div class="outer_label">
    <img class="login_logo" src="../../assets/avatar.svg" />
  </div>
</template>
<style scoped>
.outer_label {
  position: relative;
  margin: 0 auto;
  width: 120px;
  height: 120px;
  border-radius: 60px;
  box-shadow: 0 1px 3px rgba(0, 25, 104, 0.3);
}
.login_logo {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  height: 70%;
}
</style>
