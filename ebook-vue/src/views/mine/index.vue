<template>
  <div class="mine_view">
    <!-- <mine /> -->
    <div class="main">
      <avatar />
      <div class="boder"></div>
      <div class="innerList">
        <router-view />
      </div>
    </div>
  </div>
</template>
<script>
import avatar from "@/components/common/avatar";
export default {
  components: {
    avatar
  },
  methods: {}
};
</script>

<style scoped>
.main {
  padding: 55px 0 0 0;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.boder {
  width: 100%;
  height: 24px;
}

.innerList {
  width: 70%;
  margin: 0 auto;
  padding: 16px 0 0 0;
  box-shadow: 0 2px 10px rgba(0, 25, 104, 0.3);
}

@media screen and (max-width: 768px) {
  .innerList {
    width: 100%;
    overflow: auto;
  }
  .main {
    padding: 12px 0 0 0;
  }
}
</style>