<template>
  <div id="reading">
    <reader id="reader" />
    <reader-control id="readerControl" />
    <contentItem v-show="contentShow" id="contentItem" />
    <div v-if="contentShow" v-on:click="totleContentShow()" class="mask_contentShow">
      <!---->
    </div>
  </div>
</template>

<script>
import reader from "../../components/reader.vue";
import readerControl from "../../components/readerControl.vue";
import contentItem from "../../components/content.vue";
import { ebookMixin } from "@/util/mixin";
export default {
  mixins: [ebookMixin],
  components: {
    readerControl,
    reader,
    contentItem
  },
  methods: {
    totleContentShow() {
      this.setContentShow(!this.contentShow);
    }
  }
};
</script>

<style scoped>
.reading {
  display: inline;
}
.readerControl {
  overflow: visible;
  position: fixed;
  display: inline;
  z-index: 5;
  left: 50%;
  bottom: 48px;
  margin-left: 548px;
}

.mask_contentShow {
  z-index: 0;
  -webkit-font-smoothing: antialiased;
  position: fixed;
  top: 0;
  right: auto;
  bottom: auto;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  left: 0;
}

#contentItem {
  -webkit-font-smoothing: antialiased;
  line-height: 1.5;
  user-select: none;
  position: fixed;
  width: 520px;
  left: 51.44%;
  top: 0;
  bottom: 0;
  background-color: #ffffff;
  z-index: 90;
  box-shadow: -20px 0 20px 0 rgba(0, 0, 0, 0.1);
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
}
</style>
